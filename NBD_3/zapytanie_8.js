db = db.getSiblingDB("nbd");

printjson(
  db.people.updateMany(
    {
      "location.city": "Moscow",
    },
    { $set: { "location.city": "Moskwa" } }
  )
);
