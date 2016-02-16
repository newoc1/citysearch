var cityControllers = angular.module('cityControllers', []);

cityControllers.controller('cityController', ['$scope', '$location', 'City', function(
  $scope, $location, City) {
  $scope.page = 0;
  $scope.findCities = function() {
    City.rest.query({
      'page': $scope.page
    }, function(data) {
      $scope.cities = data.content;
      $scope.isFirstPage = data.first;
      $scope.isLastPage = data.last;
    });
  };

  $scope.citySize = City.citySize;
  $scope.viewCity = function(cityId) {
    $location.path('/cities/' + cityId);
  };
  $scope.nextPage = function() {
    if (!$scope.isLastPage) {
      $scope.page += 1;
      $scope.findCities();
    }
  };
  $scope.previousPage = function() {
    if (!$scope.isFirstPage) {
      $scope.page -= 1;
      $scope.findCities();
    }
  };

  $scope.findCities();
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
