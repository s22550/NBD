db = db.getSiblingDB("nbd");

printjson(
  db.people.updateMany(
    {
      first_name: "Antonio",
    },
    { $set: { hobby: "ping-pong" } }
  )
);
