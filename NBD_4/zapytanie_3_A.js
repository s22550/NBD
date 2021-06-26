db = db.getSiblingDB("nbd");

printjson(db.people.aggregate([{ $group: { _id: "$job" } }]));
