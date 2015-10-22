(function() {
	var app = angular.module('coffiURL', []);
	
	app.controller("UrlController",  ['$scope', '$http', function($scope, $http) {
		
		$scope.disabled = true;
		$scope.response = false;
		$scope.responseMessage = "";
		$scope.shortURL = "";
		$scope.error = false;
				
		this.sendData = function() {
			
			 $scope.responseMessage = "";
			 var data = {};
			 
			 if ($scope.customURL == "") {
				  data = {longURL: $scope.longURL, };
			 } else {
				 data = {longURL: $scope.longURL, shortURL: $scope.customURL, };
			 }
			
			 $http({method: 'POST', url: 'http://localhost:8080/api/url', params: data}).
			 success(function(data) {
							 
				 $scope.shortURL = data.data.shortURL;
				 $scope.responseMessage = "Generated short url -> ";
				 $scope.longURL = "";
				 $scope.customURL = "";
				 $scope.response = true;
				 
			 })
			 .error(function() {
				 $scope.responseMessage = "The long url you entered was not valid or the custom short url already exists. Please fix and try again.";
				 $scope.response = true;
				 $scope.error = true;
			 });
		};

	} ]);
	
app.controller("StatsController",  ['$scope', '$http', function($scope, $http) {
		
		$scope.statistics= "";
		
		
		
		$http({method: 'GET', url: 'http://localhost:8080/api/stats', params: {shortURL: "JiIQmX"}})
		.success(function(data) {
				 $scope.statistics = data;
		 })
		 .error(function() {
			 $scope.statistics = "Error";
		 });
		
} ]);

}) ();

