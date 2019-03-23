
app.service("tableService", function ($http) {
        this.multiplication4 = function () {
                return $http.get("multiplication4");
        };

});


