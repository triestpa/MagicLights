angular.module('LightsService', []).factory('lights', ['$http', function($http) {
  var o = {};

  o.changeColor = function(color) {
    return $http.post('/changeColor', color)
			.success(function(data) {
        console.log("Color Change Successful");
      })
      .error(function(data, status) {
        console.error('HTTP Error', status, data);
      });
  };

  return o;
}]);
