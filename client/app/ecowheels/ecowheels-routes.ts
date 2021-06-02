routeConfig.$inject = ['$stateProvider'];

export function routeConfig($stateProvider) {

    $stateProvider
        .state('homePage', {
            url: '/',
            templateUrl: '/templates/home.html',
            controller: 'homepageCtrl',
            controllerAs: 'vm'
        })


}
