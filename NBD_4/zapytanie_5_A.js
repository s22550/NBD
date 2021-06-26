db = db.getSiblingDB("nbd");

printjson(
  db.people.aggregate([
    { $match: { nationality: "Poland", sex: "Female" } },
    { $unwind: "$credit" },
    {
      $group: {
        _id: "$credit.currency",
        AvgPerCurrency: { $avg: { $convert: { input: "$credit.balance", to: "decimal" } } },
        SumPerCurrency: { $sum: { $convert: { input: "$credit.balance", to: "decimal" } } },
      },
    },
  ])
);
