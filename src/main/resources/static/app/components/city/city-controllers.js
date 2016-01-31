var cityControllers = angular.module('cityControllers', []);

cityControllers.controller('cityController', ['$scope', 'City', function(
  $scope, City) {
  $scope.cities = City.query();
}]);

cityControllers.controller('cityDetailController', ['$scope', '$routeParams',
  'City',
  function($scope, $routeParams, City) {
    City.get({
      cityId: $routeParams.cityId
    }, function(city) {
      $scope.city = city;
    });
  }
]);
