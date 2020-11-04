(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .config(routes)
        .run(configDefaults);

    routes.$inject = ['$routeProvider'];
    configDefaults.$inject = ['$rootScope'];

    function routes($routeProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: 'home.tpl.html',
            })
            .when('/clientes', {
                templateUrl: 'pages/clientes/clientes.tpl.html',
            })
            .when('/cliente', {
                templateUrl: 'pages/cliente-cadastro/cliente.tpl.html',
            })
            .when('/alugar', {
                templateUrl: 'alugar.tpl.html',
            })
            .otherwise({
                redirectTo: '/home'
            });
    }

    function configDefaults($rootScope) {
        $rootScope.listaMensagens = [];
    }

})();