db = db.getSiblingDB("nbd");

printjson(
    db.people.mapReduce(
        function () {
            emit(this.sex, parseFloat(this.weight))
        },
        function (key, values) {
            return Array.avg(values)
        },
        {
            out: { inline: 1 },
            finalize: function (key, value) {
                return {
                    sex: key,
                    weight: value
                }
            }
        }
    )
);
