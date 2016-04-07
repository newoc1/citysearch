var userServices = angular.module('userServices', ['ngResource']);

userServices.factory('User', ['$resource', '$rootElement',
  function($resource, $rootElement) {
    var csrfToken = angular.element($rootElement.find('meta[name=_csrf]')[0]).attr('value');
    var csrfHeaderName = angular.element($rootElement.find('meta[name=_csrf_header]')[0]).attr('value');
    var headers = {};
    headers[csrfHeaderName] = csrfToken;
    var rest = {};
    rest.getCurrentUser = $resource('users/me', {}, {
      query: {
        method: 'GET'
      }
    });

    return {
      rest: rest
    };
  }
]);
