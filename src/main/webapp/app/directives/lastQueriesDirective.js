app.directive('lastQueriesDirective', function() {
	return {
		restrict: 'EA',
		replace: true,
		templateUrl: 'app/directives/lastQueriesDirective.html',
		controller : ['$scope','$rootScope','$window','$http', function($scope,$rootScope,$window,$http) {
			$scope.getLatestQueries = function() {
				var url = '/query/getQueries';
				$http.get(url)
				.success(function(response) {
					$rootScope.queries = response;
				}).error(function() {
					$rootScope.globalMessagePopup(null,false);
				});
			};

			$scope.getLatestQueries();
		}]
	};
});