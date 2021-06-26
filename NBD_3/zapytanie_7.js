db = db.getSiblingDB("nbd");

printjson(
  db.people.deleteMany({
    height: { $gt: "190" },
  })
);
