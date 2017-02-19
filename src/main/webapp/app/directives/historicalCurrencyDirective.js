app.directive('historicalCurrencyDirective', function() {
	return {
		restrict: 'A',
		replace: true,
		templateUrl: 'app/directives/historicalCurrencyDirective.html',
		controller : ['$scope','$rootScope','$window','$http', '$filter', 'ParameterService',function($scope,$rootScope,$window,$http,$filter,ParameterService) {
			$rootScope.queries = [];

			var today = new Date();
			var yesterday = new Date(today.getTime() - (24*60*60*1000));
			var yearBefore = new Date(today.getTime() - (24*60*60*1000*365));
			yesterday = $filter('date')(yesterday,'dd.MM.yyyy');
			$scope.query = {};
			$('#datepicker input').datepicker({
				format: "dd.mm.yyyy",
				autoclose: true,
				startDate:yearBefore,
				endDate: yesterday,
				container: "#datepicker",
				orientation: "top"
			})
			.on('change', function() {
				$scope.query.date = $("#datepicker input").datepicker()[0].value;
				$("#selectedDate").val($("#datepicker input").datepicker()[0].value);
			});
			$('#datepicker input').datepicker("setDate",yesterday);

			$scope.fetchCurrencyQuery = function(query) {
				$scope.isLoading = true;
				$scope.rate = "";
				var url = '/query/getHistorical?baseCurrency='+query.baseCurrency.value+
				'&quoteCurrency='+query.quoteCurrency.value+
				'&date='+query.date;
				$http.get(url)
				.success(function(response) {
					$scope.isLoading = false;
					$scope.rate = response.rate;
					if($rootScope.queries.length===10) {
						$rootScope.queries.pop();
					}
					$rootScope.queries.splice(0,0,response);
				}).error(function() {
					$rootScope.globalMessagePopup(null,false);
					$scope.isLoading = false;
				});
			};

			$scope.getPRM = function(prmName) {
				ParameterService.getPRM(prmName)
				.then(function(response) {
					$scope[prmName] = response;
					$scope.query.quoteCurrency = $scope[prmName][0];
					$scope.query.baseCurrency = $scope[prmName][1];
				});
			};
		}]
	};
});