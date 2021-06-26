db = db.getSiblingDB("nbd");

printjson(
    db.people.mapReduce(
        function () {
            this.credit.forEach(function (credit) {
                emit(credit.currency, parseFloat(credit.balance))
            })
        },
        function (key, values) {
            return Array.sum(values)
        },
        {
            out: { inline: 1 },
            finalize: function (key, value) {
                return {
                    currency: key,
                    value: value
                }
            }
        }
    )
);
