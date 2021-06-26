db = db.getSiblingDB("nbd");

printjson(

    db.people.mapReduce(
        function () {
            emit(null, this.job)
        },
        function (key, values) {
            return [...new Set(values)]
        },
        {
            out: { inline: 1 },
            finalize: function (key, value) {
                return {
                    jobs: key,
                    value: value
                }
            }
        }
    )
);
