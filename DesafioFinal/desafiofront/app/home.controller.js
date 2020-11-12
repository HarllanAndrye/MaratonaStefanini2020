(function () {
    "use strict";

    angular.module('autoLocadoraApp')
        .controller('HomeController', homeController);

    homeController.$inject = ['helperFactory', 'autoLocadoraService'];

    function homeController(helper, service) {
        var vm = this;

        // Paginação
        vm.currentPage = 1;
        vm.itemsPerPage = 12;
        vm.totalItems = 120;
        vm.maxSize = 5;
        vm.lastText = "Último";
        vm.firstText = "Primeiro";
        vm.nextText = "Próximo";
        vm.previousText = "Anterior";

        // Funções
        vm.go = helper.go;
        vm.iniciar = iniciar;
        vm.listarCarros = listarCarros;
        vm.alugarCarro = alugarCarro;
        vm.pageChanged = pageChanged; // Paginação

        function iniciar() {
            return vm.listarCarros();
        }

        function pageChanged() {
            return vm.listarCarros();
        }

        function listarCarros() {
            var params = {
                "page": vm.currentPage,
                "size": vm.itemsPerPage
            };

            return service.listarComPaginacao(params)
                .then(function (_listaCarros) {
                    vm.listaCarros = _listaCarros.carros;
                    //helper.rootScopeApply();

                    // Paginação
                    vm.currentPage = _listaCarros.pagination.currentPage;
                    vm.itemsPerPage = _listaCarros.pagination.itemsPerPage;
                    vm.totalItems = _listaCarros.pagination.totalItems;
                });
        }

        function alugarCarro(placa) {
            //helper.go('/alugar/carro/' + placa);
            vm.go('/alugar/carro/' + placa);
        }
    }

})();