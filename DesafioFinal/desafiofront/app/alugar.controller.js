(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('AlugarController', alugarController);

    alugarController.$inject = ['autoLocadoraService'];

    function alugarController(service) {
        var vm = this;
        /* ***************    INIT VARIÁVEIS    *********************************** */

        vm.iniciar = iniciar;
        vm.enviarDadosAluguel = enviarDadosAluguel;
        vm.listarClientes = listarCLientes;
        vm.listarCarros = listarCarros;

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
                    console.log(data);
                }
            }
        }

        /* ***************    FUNÇÕES INSTERNAS    ******************************** */

        function listarCLientes() {
            return service.listarClientes()
                .then(function (_listaClientes) {
                    vm.listaClientes = _listaClientes;
                });
        }

        function listarCarros() {
            return service.listar()
                .then(function (_listaCarros) {
                    vm.listaCarros = _listaCarros;
                });
        }


    }

})();