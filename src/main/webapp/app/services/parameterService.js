app.service('ParameterService', ['$http','$q',function($http,$q) {
	var data = {};
	return {
		getPRM:function(prmName) {
			var deferred = $q.defer();
			$http.get('/parameter/getValue/'+prmName)
			.then(function(response) {
				data = response.data;
				deferred.resolve(data);
			},function(error) {
				deferred.reject(error);
			}
			);
			return deferred.promise;
		}
	};
}]);