angular.module('MainCtrl', []).controller('MainController', function($scope, lights) {

	$scope.red = "255000000";
	$scope.green = "000255000";
	$scope.blue = "000000255";


	$scope.colorClick = function(color) {
			console.log(color);
			lights.changeColor({color: color});
	};
});
