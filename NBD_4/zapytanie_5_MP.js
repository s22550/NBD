db = db.getSiblingDB("nbd");

printjson(
    db.people.mapReduce(
        function () {
            if (this.nationality == "Poland" && this.sex == "Female") {
                this.credit.forEach(function (credit) {
                    emit(credit.currency, { nationality: this.nationality, sex: this.sex, balance: parseFloat(credit.balance) })
                })
            }
        },
        function (key, values) {
            var polArr = []
            values.forEach(function (person) {
                polArr.push(person.balance)
            })
            var output = { avg: Array.avg(polArr), sum: Array.sum(polArr) }
            return output
        },
        {
            out: { inline: 1 },
            finalize: function (key, values) {
                return {
                    currency: key,
                    avg: values.avg,
                    sum: values.sum
                }
            }
        }
    )
);


