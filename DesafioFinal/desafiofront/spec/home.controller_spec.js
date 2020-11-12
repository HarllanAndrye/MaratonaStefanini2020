describe('HomeController', function () {

    var $controller, $rootScope, $httpBackend, $location, service;

    beforeEach(function () {
        angular.mock.autoLocadoraMockApp();
    });

    beforeEach(inject(function ($injector) {
        $controller = $injector.get('$controller');
        $rootScope = $injector.get('$rootScope');
        $httpBackend = $injector.get('$httpBackend');
        $location = $injector.get('$location');
        service = $injector.get('autoLocadoraService');
    }));

    afterEach(function () {
        $httpBackend.flush();

        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    beforeEach(function () {
        $httpBackend.whenGET('home.tpl.html').respond(200, {});
        $httpBackend.whenGET('pages/alugar/alugar.tpl.html').respond(200, {});
    });

    describe('Testando Controller', function () {
        var vm, scope;

        beforeEach(function () {
            scope = $rootScope.$new();
            vm = $controller('HomeController', { '$rootScope': $rootScope, '$scope': scope });

        });
        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': deve iniciar buscando lista de carros', function () {
            // Init
            // cria um 'spy' (espião, função mascarada que simula a função real)
            vm.listarCarros = jasmine.createSpy('listarCarros');
            // Método testado
            vm.iniciar();
            expect(vm.listarCarros).toHaveBeenCalled();
        });

        it(': deve buscar lista de carros', function () {
            // Init
            // garantir no inicio do teste que 'vm.listaCarros' está vazia
            vm.listaCarros = [];
            // garantir no inicio do teste que 'vm.totalItems' é zero
            vm.totalItems = 0;
            
            // lista mock
            var val = {
                "carros": [
                  {
                    "ano": 0,
                    "disponivel": true,
                    "marca": "string",
                    "modelo": "string",
                    "placa": "string"
                  }
                ],
                "pagination": {
                  "currentPage": 1,
                  "itemsPerPage": 5,
                  "totalItems": 10
                }
            };
            
            // cria uma promise mock (fake, falsa) e retorna lista mock
            var p = new Promise(function (resolve, reject) { resolve(val); });
            
            // cria um 'spy' (espião, função mascarada que simula a função real) e retorna a promise mock
            service.listarComPaginacao = jasmine.createSpy('listarComPaginacao').and.returnValue(p);

            // Método testado
            return vm.listarCarros()
                .then(function () {
                    expect(vm.listaCarros.length).toBeGreaterThan(0);
                    expect(vm.totalItems).toBeGreaterThan(0);
                });
        });

        it(': deve ir para a pagina de aluguel', function () {
            var placa = 'ABC1234';
            $location.path('/home');
            
            // Método testado
            vm.alugarCarro(placa);
            
            expect($location.path()).toEqual('/alugar/carro/' + placa);
        });

        it(': deve alterar a pagina da paginacao', function () {
            // Init
            vm.currentPage = 1;
            
            // cria um 'spy'
            vm.listarCarros = jasmine.createSpy('listarCarros').and.returnValue(vm.currentPage = 2);
            
            // Método testado
            vm.pageChanged();

            expect(vm.currentPage).toEqual(2);
        });

    });
});
