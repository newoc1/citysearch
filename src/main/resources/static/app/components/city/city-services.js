var cityServices = angular.module('cityServices', ['ngResource']);

cityServices.factory('City', ['$resource', '$rootElement',
  function($resource, $rootElement) {
    var csrfToken = angular.element($rootElement.find('meta[name=_csrf]')[0]).attr('value');
    var csrfHeaderName = angular.element($rootElement.find('meta[name=_csrf_header]')[0]).attr('value');
    var headers = {};
    headers[csrfHeaderName] = csrfToken;
    var rest = {};
    rest.findCities = $resource('cities/:cityId', {}, {
      query: {
        method: 'GET',
        params: {
          cityId: '',
          page: 0,
          name: ''
        }
      }
    });
    rest.cityCommodities = $resource('cities/:cityId/commodities', null, {
      give: {
        method: 'POST',
        headers: headers,
        params: {
          cityId: ''
        }
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
