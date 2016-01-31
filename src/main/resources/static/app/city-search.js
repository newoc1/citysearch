var citySearch = angular.module('citySearch', [
  'ngRoute',
  'cityControllers',
  'cityServices'
]);

citySearch.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/cities', {
      templateUrl: '/app/components/city/city-list-partial.html',
      controller: 'cityController'
    }).
    when('/cities/:cityId', {
      templateUrl: '/app/components/city/city-detail.html',
      controller: 'cityDetailController'
    }).
    otherwise({
      redirectTo: '/cities'
    });
  }
]);
