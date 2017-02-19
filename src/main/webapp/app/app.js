var app = angular.module("mainModule",['ngCookies','ng-draggable-widgets']);

app.run(function($rootScope,$timeout) {
	$rootScope.globalMessage = "";
	$rootScope.globalMessagePopup = function(messageData,isSuccess) {
		if(!isSuccess)
		{
			if(messageData) {
				messageData = messageData.message;
			}
			else {
				messageData = "Problem Occured. Please try later. "	;
			}
		}
		$rootScope.globalMessage = messageData;
		$timeout(function() {
			$rootScope.globalMessage = "";
		}, 6000);
	};
});