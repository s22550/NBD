db = db.getSiblingDB("nbd");

printjson(db.people.findOne({ nationality: "China", sex: "Female" }));
