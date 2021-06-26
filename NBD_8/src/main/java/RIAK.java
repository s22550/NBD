import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import lombok.extern.slf4j.Slf4j;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Slf4j
public class RIAK {
    public static class Car {
        public String brand;
        public String model;
        public Integer cc;
        public Integer price;

        public Car(String b, String m, Integer c, Integer p) {
            brand = b;
            model = m;
            cc = c;
            price = p;
        }
    }

    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
        RiakCluster cluster = setUpCluster();
        RiakClient client = new RiakClient(cluster);
        System.out.println("Stworzono klienta.");

        Namespace carsBucket = new Namespace("cars", StandardCharsets.UTF_8);
        Location carObjectLocation = new Location(carsBucket, "carKey");
        System.out.println("Stworzono lokalizację.");

        Car carObject = new Car("BMW", "M4", 3000, 300000);
        addCar(client, carObjectLocation, carObject);
        getCar(client, carObjectLocation);
        updateCar(client, carObjectLocation, carObject);
        getCar(client, carObjectLocation);
        deleteCar(client, carObjectLocation);
        getCar(client, carObjectLocation);

        cluster.shutdown();
    }

    private static RiakCluster setUpCluster() throws UnknownHostException {
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8087)
                .build();
        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();
        cluster.start();
        return cluster;
    }

    private static void addCar(RiakClient client, Location carObjectLocation, Car carObject) throws ExecutionException, InterruptedException {
        StoreValue storeOp = new StoreValue.Builder(carObject)
                .withLocation(carObjectLocation)
                .build();
        client.execute(storeOp);
        System.out.println("Dodano samochód.");
    }

    private static void getCar(RiakClient client, Location carObjectLocation) throws ExecutionException, InterruptedException {
        FetchValue fetchOp = new FetchValue.Builder(carObjectLocation)
                .build();
        RiakObject fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        System.out.println("Dane samochodu: " + fetchedObject);
    }

    private static void updateCar(RiakClient client, Location carObjectLocation, Car carObject) throws ExecutionException, InterruptedException {
        carObject.price = 333333;
        StoreValue updateOp = new StoreValue.Builder(carObject)
                .withLocation(carObjectLocation)
                .build();
        StoreValue.Response updateOpResp = client.execute(updateOp);
        System.out.println("Zmodyfikowano cenę samochodu.");
    }

    private static void deleteCar(RiakClient client, Location carObjectLocation) throws ExecutionException, InterruptedException {
        DeleteValue deleteOp = new DeleteValue.Builder(carObjectLocation)
                .build();
        client.execute(deleteOp);
        System.out.println("Usunięto samochód.");
    }
}
