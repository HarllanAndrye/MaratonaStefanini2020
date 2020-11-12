describe('ClienteHistoricoController', function () {

    var $controller, $rootScope, $httpBackend, service;

    beforeEach(function () {
        angular.mock.autoLocadoraMockApp();
    });

    beforeEach(inject(function ($injector) {
        $controller = $injector.get('$controller');
        $rootScope = $injector.get('$rootScope');
        $httpBackend = $injector.get('$httpBackend');
        service = $injector.get('autoLocadoraService');
    }));

    afterEach(function () {
        $httpBackend.flush();

        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    beforeEach(function () {
        $httpBackend.whenGET('home.tpl.html').respond(200, {});
    });

    describe('Testando Controller', function () {
        var vm, scope;

        beforeEach(function () {
            scope = $rootScope.$new();
            vm = $controller('ClienteHistoricoController', { '$rootScope': $rootScope, '$scope': scope });

        });
        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': testando a funcao getAlugueisCliente()', function () {
            // INIT
            vm.alugueisCliente = [];
            var val = [1, 2, 3];

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.getAlugueisCliente = jasmine.createSpy('getAlugueisCliente').and.returnValue(p);
            
            // Método testado
            return vm.getAlugueisCliente()
                .then(function () {
                    expect(vm.alugueisCliente.length).toBeGreaterThan(0);
                });
        });

       

    });
});
