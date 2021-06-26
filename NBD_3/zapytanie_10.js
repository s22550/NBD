db = db.getSiblingDB("nbd");

printjson(db.people.update({ job: "Editor" }, { $unset: { email: 1 } }, false, true));
