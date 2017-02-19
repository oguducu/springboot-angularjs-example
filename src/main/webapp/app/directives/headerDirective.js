app.directive('headerDirective', function() {
	return {
		restrict: 'EA',
		replace: true,
		templateUrl: 'app/directives/headerDirective.html',
		controller : ['$scope','$rootScope','$window','$http','AuthenticationService', function($scope,$rootScope,$window,$http,AuthenticationService) {
			if(!$window.sessionStorage.currentUser) {
				AuthenticationService.authenticate().then(function() {
					$scope.userName = $window.sessionStorage.currentUser;
				});
			}
			$scope.userName = $window.sessionStorage.currentUser;
			$scope.logout = function() {
				$http.post('logout', {}).success(function() {
					$window.sessionStorage.currentUser = "";
					$window.location.href= "/login";
				}).error(function() {
					$rootScope.authenticated = false;
				});
			};
		}]
	};
});