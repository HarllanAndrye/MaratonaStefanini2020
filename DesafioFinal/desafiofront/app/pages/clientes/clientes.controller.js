(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('ClientesController', clientesController);

        clientesController.$inject = ['autoLocadoraService', 'helperFactory'];

    function clientesController(autoLocadoraService, helper) {
        var vm = this;
       
        vm.go = helper.go;
        vm.getClientes = getClientes;

        function getClientes() {
            return autoLocadoraService.listarClientes()
                .then(clientes);

            function clientes(_clientes) {
                vm.clientes = _clientes;
            }
            
        }
    }

})();