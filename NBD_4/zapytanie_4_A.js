db = db.getSiblingDB("nbd");

printjson(
  db.people.aggregate([
    {
      $group: {
        _id: "$nationality",
        AvgBMI: { $avg: { $divide: [{ $convert: { input: "$weight", to: "decimal" } }, { $multiply: [{ $divide: [{ $convert: { input: "$height", to: "decimal" } }, 100] }, 2] }] } },
        minBMI: { $min: { $divide: [{ $convert: { input: "$weight", to: "decimal" } }, { $multiply: [{ $divide: [{ $convert: { input: "$height", to: "decimal" } }, 100] }, 2] }] } },
        maxBMI: { $max: { $divide: [{ $convert: { input: "$weight", to: "decimal" } }, { $multiply: [{ $divide: [{ $convert: { input: "$height", to: "decimal" } }, 100] }, 2] }] } },
      },
    },
  ])
);
