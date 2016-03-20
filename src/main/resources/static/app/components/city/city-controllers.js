var cityControllers = angular.module('cityControllers', []);

cityControllers.controller('cityController', ['$scope', '$location', 'City', function(
  $scope, $location, City) {
  $scope.page = 0;
  $scope.cityName = null;
  $scope.findCities = function() {
    //Need to set cityName explicitly to null so that it is not parsed as a query param
    if (!$scope.cityName) {
      $scope.cityName = null;
    } else {
      //need to reset $scope.page to 0. Otherwise we might retrieve data but be on page 12 and not see it
      $scope.page = 0;
    }
    City.rest.findCities.query({
      'page': $scope.page,
      'name': $scope.cityName
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


    $scope.getCity = function() {
      City.rest.findCities.get({
        cityId: $routeParams.cityId
      }, function(city) {
        $scope.city = city;
      });
    };
    $scope.getCity();

    $scope.giveCommodity = function(commodityName) {
      console.log($routeParams.cityId);
      City.rest.cityCommodities.give({
        'cityId': $routeParams.cityId
      }, {
        'name': commodityName
      }, function() {
        $scope.getCity();
      });
    };
  }
]);
