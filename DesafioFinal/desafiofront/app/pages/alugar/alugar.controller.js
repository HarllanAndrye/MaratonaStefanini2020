(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('AlugarController', alugarController);

    alugarController.$inject = ['autoLocadoraService', 'helperFactory', '$routeParams'];

    function alugarController(service, helper, $routeParams) {
        var vm = this;
        /* ***************    INIT VARIÁVEIS    *********************************** */

        vm.listaClientes = [];
        vm.listaCarros = [];

        vm.form = {
            "clienteId": "",
            "placaCarro": "",
            "devolucaoEmDias": ""
        };

        vm.qtdDias = [
            {
                "number": 1,
                "text": "dia"
            }
        ];
        for (let index = 2; index <= 31; index++) {
            vm.qtdDias.push(
                {
                    "number": index,
                    "text": "dias"
                }
            );
        }

        vm.iniciar = iniciar;
        vm.enviarDadosAluguel = enviarDadosAluguel;
        vm.listarClientes = listarCLientes;
        vm.listarCarros = listarCarros;

        /* ***************    FUNÇÕES EXECUTADAS NA VIEW (HMTL)    **************** */

        function iniciar() {
            if ($routeParams.placa) {
                listarCLientes();

                return service.getCarro($routeParams.placa)
                    .then(function (_carro) {
                        vm.listaCarros.push(_carro);
                        vm.form.placaCarro = _carro.placa;
                    });
            } else if ($routeParams.id) {
                listarCarros();

                return service.getCliente($routeParams.id)
                    .then(function (_cliente) {
                        vm.listaClientes.push(_cliente);
                        /**
                         * _cliente.id é do tipo number, mas o select na view só estar reconhecendo apenas string na variável
                         * "vm.form.clienteId", por isso a conversão.
                         */
                        vm.form.clienteId = _cliente.id.toString();
                    });
            } else {
                listarCLientes();
                listarCarros();
            }
        }

        function enviarDadosAluguel() {
            if (typeof vm.form != 'undefined' && typeof vm.form.clienteId != 'undefined' && typeof vm.form.placaCarro != 'undefined') {
                return service.cadastrarAluguel(vm.form)
                    .then(response);

                function response(data) {
                    if (data.error) {
                        helper.addAlerta(data.msg, 'warning', '');
                    } else {
                        helper.go('/alugueis');
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

    }

})();