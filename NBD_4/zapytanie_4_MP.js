db = db.getSiblingDB("nbd");

printjson(
    db.people.mapReduce(
        function () {
            emit(this.nationality, { weight: this.weight, height: this.height })
        },
        function (key, values) {
            var output = { avg: 0, min: 0, max: 0 }
            var bmiArr = []
            values.forEach(function (value) {
                bmiArr.push(value.weight / (value.height / 100 ^ 2))
            })
            output.avg = Array.avg(bmiArr)
            output.min = Math.min(...bmiArr)
            output.max = Math.max(...bmiArr)
            return output
        },
        {
            out: { inline: 1 },
            finalize: function (key, value) {
                return {
                    nationality: key,
                    avg: value.avg,
                    min: value.min,
                    max: value.max
                }
            }
        }
    )
);
