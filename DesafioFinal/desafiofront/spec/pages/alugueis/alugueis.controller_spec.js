describe('AlugueisController', function () {

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
            vm = $controller('AlugueisController', { '$rootScope': $rootScope, '$scope': scope });

        });
        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': deve iniciar buscando lista de alugueis', function () {
            vm.alugueis = [];
            
            var val = {
                "clienteId": 1,
                "placaCarro": "ABC1234"
              };
            
            var p = new Promise(function (resolve, reject) { resolve(val); });
            // cria um 'spy'
            service.listarAlugueis = jasmine.createSpy('listarAlugueis').and.returnValue(p);

            // Método testado
            return vm.getAlugueis()
                .then(function () {
                    expect(Object.keys(vm.alugueis).length).toBeGreaterThan(0);
                });
        });

        it(': testando a funcao devolverCarro()', function () {
            vm.alugueis = [];
            
            var valCarro = 201;
            var valAlugueis = {"teste": "valor"};
            var params = {
                "clienteId": 1,
                "placaCarro": "ABC1234"
            };
            
            var p2 = new Promise(function (resolve, reject) { resolve(valAlugueis); });
            service.listarAlugueis = jasmine.createSpy('listarAlugueis').and.returnValue(p2);
            
            var p = new Promise(function (resolve, reject) { resolve(valCarro); });
            service.devolverCarro = jasmine.createSpy('devolverCarro').and.returnValue(p);

            // Método testado
            return vm.devolverCarro(params)
                .then(function () {
                    expect(Object.keys(vm.alugueis).length).toBeGreaterThan(0);
                });
        });

        it(': testando a funcao devolverCarro() sem retorno', function () {
            // Init
            vm.alugueis = [];
            
            // Entrada
            var valCarro = 400;
            var params = {
                "clienteId": 1,
                "placaCarro": "ABC1234"
            };
            
            var p = new Promise(function (resolve, reject) { resolve(valCarro); });
            service.devolverCarro = jasmine.createSpy('devolverCarro').and.returnValue(p);

            // Método testado
            return vm.devolverCarro(params)
                .then(function () {
                    expect(Object.keys(vm.alugueis).length).toEqual(0);
                });
        });

    });
});
