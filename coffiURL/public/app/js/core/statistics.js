(function() {
	var app = angular.module('appStats', ['chart.js', 'googlechart']);
	
	app.controller("StatsController",  ['$scope', '$http', function($scope, $http) {
		
		$http({method: 'GET', url: 'http://localhost:8080/api/stats', params: {shortURL: "JiIQmX"}})
		.success(function(data) {
			 $scope.statistics = data;
		 })
		 .error(function() {
			 $scope.statistics = "Error";
		 });
			
		$scope.browserLabels = ["Firefox", "Chrome"];
		$scope.browserData = [3, 5];
		
		$scope.platformLabels = ["Linux", "Windows"];
		$scope.platformData = [100, 50];
		
		var chart1 = {};
		  chart1.type = "GeoChart";
		  chart1.data = [
		        ['Locale', 'Clicks'],
		        ['IT', 22]
		      ];

		  chart1.options = {
		      width: 800,
		      height: 400,
		      colorAxis: {colors: ['#aec7e8', '#1f77b4']},
		      displayMode: 'regions'
		  };

		  chart1.formatters = {
		     number : [{
		       columnNum: 1,
		       pattern: "#,##0"
		     }]
		   };

		  $scope.chart = chart1;
		
		
} ]);
	
}) ();