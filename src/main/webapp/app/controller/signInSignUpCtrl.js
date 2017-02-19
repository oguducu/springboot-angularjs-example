app.controller("SignInSignUpCtrl",['$scope','$timeout','$http','$window','$rootScope','ParameterService','AuthenticationService',
                                   function($scope,$timeout,$http,$window,$rootScope,ParameterService,AuthenticationService){
	var messageData = {};
	$scope.currentUser = $window.sessionStorage.currentUser;
	$scope.signIn = true;
	$scope.user = {};
	$scope.user.address = {};

	//Date of birth setting
	var maxYear = new Date().getFullYear()-14;
	$('#datepicker input').datepicker({
		format: "dd.mm.yyyy",
		endDate: "31.12."+maxYear,
		autoclose: true,
		defaultViewDate: { year: maxYear, month: 0, day: 01 },
		container: "#datepicker",
		orientation: "top"
	})
	.on('change', function() {
		$scope.user.date = $("#datepicker input").datepicker()[0].value;
		$("#selectedDate").val($("#datepicker input").datepicker()[0].value);
	});
	$('#datepicker input').datepicker("setDate","01.01."+maxYear);

	//If user has already logged in, then redirect user to home page
	AuthenticationService.authenticate("/home");

	$scope.getPRM = function(prmName,select) {
		ParameterService.getPRM(prmName)
		.then(function(response) {
			$scope[prmName] = response;
			if(select) {
				$scope.user.address.country = $scope[prmName][0];
			}
		});
	};

	$scope.login = function(userForm,user) {
		if(userForm.$valid) {
			$scope.isLoading = true;
			$http.post('/login',$.param(user),{
				headers : {
					"content-type" : "application/x-www-form-urlencoded"
				}})
				.success(function() {
					AuthenticationService.authenticate()
					.then(function(response) {
						if(response) {
							$window.location.href="/home";
						}
						else {
							messageData.message = "Email or password is wrong!";
							$rootScope.globalMessagePopup(messageData,false);
							user.password = "";
							$scope.isLoading = false;
						}
					});
				}).error(function() {
					$rootScope.globalMessagePopup(null,false);
					$scope.isLoading = false;
				});
		}
	};

	$scope.openCloseSignIn = function(isSignIn) {
		$scope.signIn = isSignIn;
	};

	$scope.signUp = function(signUpForm,user) {
		if(signUpForm.$valid) {
			$scope.isLoading = true;
			var tempUser = angular.copy(user);
			var address = angular.copy(user.address);
			address.country = user.address.country.value;
			address.city = user.address.city.value;
			tempUser.address = [];
			tempUser.address.push(address);
			$http.post('/user/create',tempUser)
			.success(function() {
				messageData.message = "You successfully sign up. Thank you for choosing us!";
				$rootScope.globalMessagePopup(messageData,false);
				$timeout(function() {
					$scope.login(signUpForm,user);
				}, 3000);

			}).error(function(error, status) {
				if(status===409){
					messageData.message = "Email has been registered before";
					$rootScope.globalMessagePopup(messageData,false);
				}
				else {
					$rootScope.globalMessagePopup(null,false);
				}
				$scope.isLoading = false;
			});
		}
	};
}]);