app.controller("homeCtrl",function($scope,$rootScope,$timeout,$http,$cookies,$compile,$q,$log) {

	var previousWidgets = [
	                       {class: 'live',isVisible: 'true'},
	                       {class: 'historical',isVisible: 'false'},
	                       {class: 'last',isVisible: 'true'}
	                       ];
	var homePageWidgets;

	var getHomePageWidgets = function() {
		var deferred = $q.defer();
		var url = '/userSettings';
		$http.get(url)
		.success(function(response) {
			homePageWidgets = response.homePageWidgets;
			deferred.resolve(true);
			$scope.loadComplete = true;
		}).error(function() {
			deferred.resolve(false);
			$rootScope.globalMessagePopup(null,false);
		});
		return deferred.promise;
	};
	
	var initializeHomePage = function() {
		homePageWidgets = $cookies.get('homePageWidgets');
		if(homePageWidgets) {
			$scope.widgets = JSON.parse(homePageWidgets);
			previousWidgets = JSON.parse(homePageWidgets);
			$scope.loadComplete = true;
			return;
		}
		else {
			getHomePageWidgets().then(function(data){
				if(!data || !homePageWidgets) {
					$scope.widgets = angular.copy(previousWidgets);
					$cookies.put('homePageWidgets',JSON.stringify(previousWidgets, null, ''));
					$scope.loadComplete = true;
					return;
				}
				$scope.widgets = JSON.parse(homePageWidgets);
				$scope.loadComplete = true;
			});
		}
	};
	
	initializeHomePage();

	var checkIfWidgetSame = function(newWidgets) {
		for(var i=0;i<newWidgets.length;i++){
			if(previousWidgets[i].class !== newWidgets[i].class) {
				return false;
			}
		}
		return true;
	};

	var saveHomePageWidgets = function(jsonString) {
		var userSettings = {};
		userSettings.homePageWidgets = jsonString;
		var url = '/userSettings';
		$http.post(url,userSettings)
		.success(function() {
			$log.info("Settings are saved");
		}).error(function() {
			$log.error("Problem occurder while saving settings");
		})
	};

	$scope.moveWidget = function(drag) {
		var newWidgets = drag.source.group;
		if(!checkIfWidgetSame(newWidgets)) {
			var widgetsArray = [];
			angular.forEach(newWidgets, function(newWidget){
				delete newWidget["$$hashKey"]; 
				widgetsArray.push(newWidget);
			});
			saveHomePageWidgets(JSON.stringify(widgetsArray, null, ""));
			$cookies.put('homePageWidgets',JSON.stringify(widgetsArray, null, ""));
			previousWidgets = widgetsArray;
		}
	};

	$rootScope.globalMessage = "";	
});