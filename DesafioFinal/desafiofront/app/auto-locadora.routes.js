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
            .when('/carro/cadastro', {
                templateUrl: 'pages/carro-cadastro/carro-form.tpl.html',
            })
            .when('/clientes', {
                templateUrl: 'pages/clientes/clientes.tpl.html',
            })
            .when('/cliente', {
                templateUrl: 'pages/cliente-cadastro/cliente.tpl.html',
            })
            .when('/cliente/alugueis/:id', {
                templateUrl: 'pages/cliente-historico/cliente-historico.tpl.html',
            })
            .when('/alugueis', {
                templateUrl: 'pages/alugueis/alugueis.tpl.html',
            })
            .when('/alugar', {
                templateUrl: 'pages/alugar/alugar.tpl.html',
            })
            .when('/alugar/cliente/:id', {
                templateUrl: 'pages/alugar/alugar.tpl.html',
            })
            .when('/alugar/carro/:placa', {
                templateUrl: 'pages/alugar/alugar.tpl.html',
            })
            .otherwise({
                redirectTo: '/home'
            });
    }

    function configDefaults($rootScope) {
        $rootScope.listaMensagens = [];
    }

})();