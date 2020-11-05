(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('AlugarController', alugarController);

    alugarController.$inject = ['autoLocadoraService', 'helperFactory'];

    function alugarController(service, helper) {
        var vm = this;
        /* ***************    INIT VARIÁVEIS    *********************************** */

        vm.alugados = "";

        vm.iniciar = iniciar;
        vm.enviarDadosAluguel = enviarDadosAluguel;
        vm.listarClientes = listarCLientes;
        vm.listarCarros = listarCarros;
        //vm.listarAlugueis = listarAlugueis;

        /* ***************    FUNÇÕES EXECUTADAS NA VIEW (HMTL)    **************** */

        function iniciar() {
            listarCLientes();
            listarCarros();
        }

        function enviarDadosAluguel() {
            if (typeof vm.form != 'undefined' && typeof vm.form.clienteId != 'undefined' && typeof vm.form.placaCarro != 'undefined') {
                return service.cadastrarAluguel(vm.form)
                    .then(response);

                function response(data) {
                    if (data.error) {
                        helper.addAlerta(data.msg, 'warning', '');
                    } else {
                        //TODO retornar para a página que lista os alugueis
                    }
                }
            }
        }

        /* ***************    FUNÇÕES INTERNAS    ******************************** */

        function listarCLientes() {
            return service.listarClientesSemAluguel()
                .then(function (_listaClientes) {
                    vm.listaClientes = _listaClientes;
                });
        }

        function listarCarros() {
            return service.listarCarrosDisponiveis()
                .then(function (_listaCarros) {
                    vm.listaCarros = _listaCarros;
                });
        }

        /*function listarAlugueis() {
            return service.listarAlugueis()
                .then(function (_listaAlugueis) {
                    vm.alugados = _listaAlugueis;
                    console.log(vm.alugados[0].clienteId);
                    console.log(vm.alugados[0].placaCarro);
                });
        }*/

    }

})();