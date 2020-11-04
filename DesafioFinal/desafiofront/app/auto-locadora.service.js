(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .service('autoLocadoraService', autoLocadoraService);

    autoLocadoraService.$inject = ['$http', 'constantes', 'helperFactory'];

    function autoLocadoraService($http, constantes, helper) {

        return {
            listar: listar,
            cadastrarCliente: cadastrarCliente,
            listarClientes: listarClientes,
            cadastrarAluguel: cadastrarAluguel,
            consultaCEP: consultaCEP
        }

        // ======================================

        function listar() {
            return $http.get(constantes.URL_BASE + '/carro')
                .then(function (response) {
                    return response.data;
                })
                .catch(helper.sendError);
        }

        function cadastrarCliente(params) {
            return $http.post(constantes.URL_BASE + '/cliente', params)
                .then(function (response) {
                    return response.data;
                }).catch(function (error) {
                    return error;
                });
            //.catch(helper.sendError);
        }

        function listarClientes() {
            return $http.get(constantes.URL_BASE + '/cliente')
                .then(function (response) {
                    return response.data;
                })
                .catch(helper.sendError);
        }

        function cadastrarAluguel(params) {
            return $http.post(constantes.URL_BASE + '/cliente/aluguel', params)
                .then(function (response) {
                    return response.data;
                })
                .catch(helper.sendError);
        }

        function consultaCEP(cep) {
            return $http.get('https://viacep.com.br/ws/' + cep + '/json/')
                .then(function (response) {
                    return response.data;
                })
                .catch(helper.sendError);
        }

    }


})();