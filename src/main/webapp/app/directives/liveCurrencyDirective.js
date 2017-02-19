app.directive('liveCurrencyDirective', function($interval) {
	return {
		restrict: 'EA',
		replace: true,
		templateUrl: 'app/directives/liveCurrencyDirective.html',
		controller : ['$scope','$rootScope','$window','$http', function($scope,$rootScope,$window,$http) {
			var promise;
			var liveCurrencies = function() {
				$http.get('/query/getLive')
				.success(function(response) {
					$scope.liveCurrencies = response;
				}).error(function() {
					$interval.cancel(promise);
				})
			};
			liveCurrencies();
			promise = $interval(liveCurrencies,60000);
		}]
	};
});