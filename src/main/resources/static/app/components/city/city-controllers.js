var cityControllers = angular.module('cityControllers', []);

cityControllers.controller('cityController', ['$scope', '$location', 'City', function(
  $scope, $location, City) {
  $scope.cities = City.rest.query();
  $scope.citySize = City.citySize;
  $scope.viewCity = function(cityId) {
    $location.path('/cities/' + cityId);
  };
}]);

cityControllers.controller('cityDetailController', ['$scope', '$routeParams',
  'City',
  function($scope, $routeParams, City) {
    City.rest.get({
      cityId: $routeParams.cityId
    }, function(city) {
      $scope.city = city;
    });
  }
]);
