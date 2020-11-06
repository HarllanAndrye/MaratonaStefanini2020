(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('ClienteHistoricoController', clienteHistoricoController);

    clienteHistoricoController.$inject = ['autoLocadoraService', '$routeParams'];

    function clienteHistoricoController(autoLocadoraService, $routeParams) {
        var vm = this;

        vm.getAlugueisCliente = getAlugueisCliente;

        function getAlugueisCliente() {
            return autoLocadoraService.getAlugueisCliente($routeParams.id)
                .then(function (_alugueisCliente) {
                    vm.alugueisCliente = _alugueisCliente;
                });
        }

    }

})();