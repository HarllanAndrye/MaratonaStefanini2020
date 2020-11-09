(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('ClienteController', clienteController);

    clienteController.$inject = ['autoLocadoraService', 'helperFactory', '$http'];

    function clienteController(autoLocadoraService, helper, $http) {
        var vm = this;

        vm.enviarDadosCliente = enviarDadosCliente;
        vm.consultaCEP = consultaCEP;

        // Obter estados do arquivo json
        $http.get('assets/estados.json')
            .then(function (response) {
                vm.estados = response.data;
            });

        function enviarDadosCliente() {
            return autoLocadoraService.cadastrarCliente(vm.form)
                .then(response);

            function response(data) {
                if (data.status == 400) {
                    helper.addAlerta(data.data.error, 'warning', '');
                } else {
                    helper.addAlerta('Cadastrado com sucesso', 'success', '');
                    vm.form = "";
                    helper.go('/clientes');
                }
            }
        }

        function consultaCEP() {
            if (typeof vm.form != 'undefined' && typeof vm.form.endereco.cep != 'undefined') {
                return autoLocadoraService.consultaCEP(vm.form.endereco.cep)
                    .then(response);

                function response(data) {
                    vm.form.endereco.logradouro = data.logradouro;
                    vm.form.endereco.complemento = data.complemento;
                    vm.form.endereco.bairro = data.bairro;
                    vm.form.endereco.cidade = data.localidade;
                    vm.form.endereco.uf = data.uf;
                }
            }
        }
    }

})();