var cityServices = angular.module('cityServices', ['ngResource']);

cityServices.factory('City', ['$resource',
  function($resource) {
    var rest = $resource('cities/:cityId', {}, {
      query: {
        method: 'GET',
        params: {
          cityId: ''
        },
        isArray: true
      }
    });

    var citySize = function(city) {
      var citySizeClass;
      if (city.populationCount > 5000000) {
        citySizeClass = 'big-city';
      } else if (city.populationCount > 1000000) {
        citySizeClass = 'medium-city';
      } else {
        citySizeClass = 'small-city';
      }
      return citySizeClass;
    };

    return {
      rest: rest,
      citySize: citySize
    };
  }
]);
