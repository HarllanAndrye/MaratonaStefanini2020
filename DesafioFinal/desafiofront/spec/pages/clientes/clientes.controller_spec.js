describe('ClientesController', function () {

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
        $httpBackend.whenGET('pages/cliente-historico/cliente-historico.tpl.html').respond(200, {});
    });

    describe('Testando Controller', function () {
        var vm, scope;

        beforeEach(function () {
            scope = $rootScope.$new();
            vm = $controller('ClientesController', { '$rootScope': $rootScope, '$scope': scope });

        });
        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': testando a funcao getClientes()', function () {
            // Init
            vm.clientes = []
            var val = [1, 2, 3];

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.listarClientes = jasmine.createSpy('listarClientes').and.returnValue(p);
            
            // Método testado
            return vm.getClientes()
                .then(function () {
                    expect(vm.clientes.length).toBeGreaterThan(0);
                });
        });

        it(': testando a funcao alugarCarro()', function () {
            // Init
            var clienteId = 1;
            $location.path('/clientes');
            
            // Método testado
            vm.alugarCarro(clienteId);
            
            expect($location.path()).toEqual('/alugar/cliente/1');
        });

        it(': testando a funcao verHistorico()', function () {
            // Init
            var clienteId = 1;
            $location.path('/clientes');
            
            // Método testado
            vm.verHistorico(clienteId);
            
            expect($location.path()).toEqual('/cliente/alugueis/1');
        });

    });
});
