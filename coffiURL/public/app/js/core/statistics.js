(function() {
	var app = angular.module('appStats', ['chart.js', 'googlechart', 'ngRoute', 'ui.bootstrap', 'ui.bootstrap.datetimepicker']).
	   config(['$routeProvider' , '$locationProvider', 
	           function($routeProvider, $locationProvider) {
	           $locationProvider.html5Mode({
	        	   enabled: true,
	        	   requireBase: true
	           });  
	   }]);
	
	app.controller("StatsController",  ['$scope', '$http', '$location', '$window', function($scope, $http, $location, $window) {
		
		var shortURL = $location.search()['shortURL']; 
		var fromTime;
		var toTime;
		
		this.getPeriodStats = function() {
			var fromTime = Date.parse($scope.fromTime);
			var toTime = Date.parse($scope.toTime);
		}
		
		$http({method: 'GET', url: 'http://localhost:8080/api/stats', params: {shortURL: shortURL, fromTime: fromTime, toTime: toTime}})
		.success(function(data) {
			
			var browser_keys = [];
			var browser_values = [];
			
			var platform_keys = [];
			var platform_values = [];
			
			var location = [['Locale','Clicks']];
			
			angular.forEach(data.data.statistics.browser, function(value,key) {
				browser_keys.push(key);
				browser_values.push(value);
			});
			
			angular.forEach(data.data.statistics.platform, function(value,key) {
				platform_keys.push(key);
				platform_values.push(value);
			});
			
			angular.forEach(data.data.statistics.location, function(value,key) {
				var loc = [key,value]
				location.push(loc);
			});
			
			$scope.longURL = data.longURL;
			$scope.shortURL = shortURL; 
			
			$scope.browserLabels = browser_keys;
			$scope.browserData = browser_values;
			
			$scope.platformLabels = platform_keys;
			$scope.platformData = platform_values;
			
			var chart1 = {};
			  chart1.type = "GeoChart";
			  chart1.data = location;
			  
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
			
			$scope.numOfClicks = data.data.statistics.others.numOfClicks;
	 		$scope.distinctIpAdress = data.data.statistics.others.distinctIpAdresses;
	
		}). error(function(status) {
			$window.location.href = "/404.html";
		});				
} ]);
	
}) ();