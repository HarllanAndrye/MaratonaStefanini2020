(function () {
    "'use strict';\n";

    angular.module('autoLocadoraApp')
        .controller('AlugarController', alugarController);

    alugarController.$inject = ['autoLocadoraService', 'helperFactory', '$routeParams'];

    function alugarController(service, helper, $routeParams) {
        var vm = this;

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
        for (var index = 2; index <= 31; index++) {
            vm.qtdDias.push(
                {
                    "number": index,
                    "text": "dias"
                }
            );
        }

        vm.iniciar = iniciar;
        vm.enviarDadosAluguel = enviarDadosAluguel;
        vm.clientesSemAluguel = clientesSemAluguel;
        vm.listarCarros = listarCarros;


        function iniciar() {
            if ($routeParams.placa) {
                clientesSemAluguel();

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
                clientesSemAluguel();
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

        function clientesSemAluguel() {
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