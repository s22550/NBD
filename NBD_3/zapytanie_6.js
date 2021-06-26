db = db.getSiblingDB("nbd");

printjson(
  db.people.insert({
    sex: "Male",
    first_name: "Paweł",
    last_name: "Kamiński",
    job: "student",
    email: "pkaminski@mail.com",
    location: {
      city: "Warszawa",
      address: {
        streetname: "Miła",
        streetnumber: "20",
      },
    },
    description: "lorem ipsum",
    height: "194",
    weight: "80",
    birth_date: "1997-09-01T10:0:00Z",
    nationality: "Poland",
    credit: [
      {
        type: "jcb",
        number: "1234",
        currency: "PLN",
        balance: "5000.00",
      },
    ],
  })
);
