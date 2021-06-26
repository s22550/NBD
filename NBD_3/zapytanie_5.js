db = db.getSiblingDB("nbd");

printjson(
  db.people
    .find(
      {
        birth_date: { $gte: "2000-01-01T00:00:00.000Z", $lt: "2099-12-31T23:59:59.999Z" },
      },
      { _id: 0, first_name: 1, last_name: 1, location: 1 }
    )
    .toArray()
);
