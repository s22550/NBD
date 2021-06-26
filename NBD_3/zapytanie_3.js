db = db.getSiblingDB("nbd");

printjson(db.people.find({ nationality: "Germany", sex: "Male" }).toArray());
