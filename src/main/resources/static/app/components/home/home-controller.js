var citySearchApp = angular.module('citySearchApp', []);

citySearchApp.controller('HomeController', function($scope, $http) {
  $http.get('cities').success(function(cities) {
    $scope.cities = cities;
  });
});
