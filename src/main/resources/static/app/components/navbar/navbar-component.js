function navbarController($scope, $http, $window, $rootElement) {
  this.logout = function() {
    var csrfToken = angular.element($rootElement.find('meta[name=_csrf]')[0]).attr('value');
    var csrfHeaderName = angular.element($rootElement.find('meta[name=_csrf_header]')[0]).attr('value');
    var headers = {};
    headers[csrfHeaderName] = csrfToken;
    $http({
      method: 'POST',
      url: '/logout',
      headers: headers
    }).then(function() {
      $window.location.href = "/login?logout";
    });
  };
}
angular.module('citySearch').component('csNavbar', {
  templateUrl: '/app/components/navbar/navbar-component.html',
  controller: navbarController,
  bindings: {
    onLogout: '&'
  }
});
