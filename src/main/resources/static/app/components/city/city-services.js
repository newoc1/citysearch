var cityServices = angular.module('cityServices', ['ngResource']);

cityServices.factory('City', ['$resource',
  function($resource) {
    return $resource('cities/:cityId', {}, {
      query: {
        method: 'GET',
        params: {
          cityId: ''
        },
        isArray: true
      }
    });
  }
]);
