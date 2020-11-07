(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('CarroController', carroController);

    carroController.$inject = ['helperFactory', 'autoLocadoraService'];

    function carroController(helper, service) {
        var vm = this;

        vm.cadastrar = cadastrar;

        function cadastrar() {
            return service.cadastrarCarro(vm.form)
                    .then(response);
    
            function response(data) {
                if (data.error) {
                    helper.addAlerta(data.msg, 'warning', '');
                } else {
                    helper.go('/home');
                }
            }
        }
    }

})();