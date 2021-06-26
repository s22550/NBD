db = db.getSiblingDB("nbd");

printjson(
  db.people.aggregate([
    {
      $unwind: "$credit",
    },
    { $group: { _id: "$credit.currency", balanceSumPerCurrency: { $sum: { $convert: { input: "$credit.balance", to: "decimal" } } } } },
  ])
);
