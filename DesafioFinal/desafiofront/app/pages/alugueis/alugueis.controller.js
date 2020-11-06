(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('AlugueisController', alugueisController);

        alugueisController.$inject = ['autoLocadoraService', 'helperFactory'];

    function alugueisController(service, helper) {
        var vm = this;

        vm.go = helper.go;
        vm.getAlugueis = getAlugueis;
        vm.devolverCarro = devolverCarro;

        function getAlugueis() {
            return service.listarAlugueis()
                .then(alugueis);

            function alugueis(_alugueis) {
                vm.alugueis = _alugueis;
            }
            
        }

        function devolverCarro(aluguel) {
            var params = {
                "clienteId": aluguel.clienteId,
                "placaCarro": aluguel.placaCarro
            };

            return service.devolverCarro(params)
                .then(devolucao);

            function devolucao(_resp) {
                if (_resp == 201) {
                    getAlugueis();
                }
            }
        }
    }

})();