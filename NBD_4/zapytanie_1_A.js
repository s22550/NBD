db = db.getSiblingDB("nbd");

printjson(
  db.people.aggregate([{ $group: { _id: "$sex", heightAvg: { $avg: { $convert: { input: "$height", to: "decimal" } } }, weightAvg: { $avg: { $convert: { input: "$weight", to: "decimal" } } } } }])
);
