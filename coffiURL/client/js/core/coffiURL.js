var app = angular.module('coffiURL', []);

app.controller('urlForm', function($scope, $http) {
    $scope.send = function() {alert("ciao");};
});