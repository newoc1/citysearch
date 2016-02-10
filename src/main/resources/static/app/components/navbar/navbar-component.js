function navbarController($scope, $http, $window, $rootElement) {
  $scope.csrfToken = angular.element($rootElement.find('meta[name=_csrf]')[0]).attr('value');
  $scope.csrfParameterName = angular.element($rootElement.find('meta[name=_csrf_parameterName]')[0]).attr('value');
}
angular.module('citySearch').component('csNavbar', {
  templateUrl: '/app/components/navbar/navbar-component.html',
  controller: navbarController
});
