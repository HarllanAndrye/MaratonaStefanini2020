(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('ClienteController', clienteController);

    clienteController.$inject = ['autoLocadoraService', 'helperFactory'];

    function clienteController(autoLocadoraService, helper) {
        var vm = this;
        /* ***************    INIT VARIÁVEIS    *********************************** */

        vm.enviarDadosCliente = enviarDadosCliente;
        vm.consultaCEP = consultaCEP;

        /* ***************    FUNÇÕES EXECUTADAS NA VIEW (HMTL)    **************** */
        function enviarDadosCliente() {
            return autoLocadoraService.cadastrarCliente(vm.form)
                    .then(response);
    
            function response(data) {
                if (data.status == 400) {
                    helper.addAlerta(data.data.error, 'warning', '');
                } else {
                    helper.addAlerta('Cadastrado com sucesso', 'success', '');
                    vm.form = "";
                }
            }
        }

        function consultaCEP(cep) {
            return autoLocadoraService.consultaCEP(cep)
                    .then(response);
    
                function response(data) {
                    console.log(data);
                }
        }
       
        /* ***************    FUNÇÕES INSTERNAS    ******************************** */


    }

})();