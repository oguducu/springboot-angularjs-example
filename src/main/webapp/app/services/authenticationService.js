app.service('AuthenticationService', ['$http','$q','$window',function($http,$q,$window) {
	return {
		authenticate:function(url) {
			var deferred = $q.defer();
			$http.get('user')
			.then(function(response) {
				if (response.data.name) {
					$window.sessionStorage.currentUser = response.data.principal.firstName + " " + response.data.principal.lastName;
					if(url) {
						$window.location.href=url;
					}
				} else {
					$window.sessionStorage.currentUser = "";
				}
				deferred.resolve(response.data);
			},function(error) {
				$window.sessionStorage.currentUser = "";
				deferred.reject(error);
			});
			return deferred.promise;
		}
	};
}]);