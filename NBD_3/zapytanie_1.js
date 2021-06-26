db = db.getSiblingDB("nbd");

printjson(db.people.findOne());
