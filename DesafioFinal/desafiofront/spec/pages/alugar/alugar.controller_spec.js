describe('AlugarController', function () {

    var $controller, $rootScope, $httpBackend, $location, service, $routeParams, helper;

    beforeEach(function () {
        angular.mock.autoLocadoraMockApp();
    });

    beforeEach(inject(function ($injector) {
        $controller = $injector.get('$controller');
        $rootScope = $injector.get('$rootScope');
        $httpBackend = $injector.get('$httpBackend');
        $location = $injector.get('$location');
        service = $injector.get('autoLocadoraService');
        $routeParams = $injector.get('$routeParams');
        helper = $injector.get('helperFactory');
    }));

    afterEach(function () {
        $httpBackend.flush();

        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    beforeEach(function () {
        $httpBackend.whenGET('home.tpl.html').respond(200, {});
        $httpBackend.whenGET('pages/alugar/alugar.tpl.html').respond(200, {});
        $httpBackend.whenGET('pages/alugueis/alugueis.tpl.html').respond(200, {});
    });

    describe('Testando Controller', function () {
        var vm, scope;

        beforeEach(function () {
            scope = $rootScope.$new();
            vm = $controller('AlugarController', { '$rootScope': $rootScope, '$scope': scope });
        });

        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': testando a funcao inciar() passando a placa do carro como parametro', function () {
            vm.listaCarros = [];
            $routeParams.placa = "ABC1234";

            var val = [1, 2, 3];
            var val2 = {
                placa: $routeParams.placa
            };

            var p = new Promise(function (resolve, reject) { resolve(val); })
            service.listarClientesSemAluguel = jasmine.createSpy('listarClientesSemAluguel').and.returnValue(p);

            var p2 = new Promise(function (resolve, reject) { resolve(val2); })
            service.getCarro = jasmine.createSpy('getCarro').and.returnValue(p2);
            
            return vm.iniciar()
                .then(function () {
                    expect(vm.listaCarros.length).toBeGreaterThan(0);
                });
        });

        it(': testando a funcao inciar() passando o id do cliente como parametro', function () {
            // =============== PRIMEIRO TESTE =============== //
            var val = [1, 2, 3];

            var p = new Promise(function (resolve, reject) { resolve(val); })
            service.listarClientesSemAluguel = jasmine.createSpy('listarClientesSemAluguel').and.returnValue(p);

            var p2 = new Promise(function (resolve, reject) { resolve(val); })
            service.listarCarrosDisponiveis = jasmine.createSpy('listarCarrosDisponiveis').and.returnValue(p2);

            vm.iniciar();
            expect(vm.listaClientes.length).not.toBeGreaterThan(0);


            // =============== SEGUNDO TESTE =============== //
            vm.listaClientes = [];
            $routeParams.id = 1;

            var val = [1, 2, 3];
            var val2 = {
                id: $routeParams.id
            };

            var p = new Promise(function (resolve, reject) { resolve(val); })
            service.listarCarrosDisponiveis = jasmine.createSpy('listarCarrosDisponiveis').and.returnValue(p);

            var p2 = new Promise(function (resolve, reject) { resolve(val2); })
            service.getCliente = jasmine.createSpy('getCliente').and.returnValue(p2);
            

            return vm.iniciar()
                .then(function () {
                    expect(vm.listaClientes.length).toBeGreaterThan(0);
                });
        });

        it(': testando a funcao enviarDadosAluguel()', function () {
            // Primeiro teste
            $location.path('/alugar');
            vm.form = 'undefined';
            vm.enviarDadosAluguel();
            expect($location.path()).toEqual('/alugar');

            // Segundo teste
            vm.form = {
                "clienteId": 1,
                "placaCarro": "ABC1234",
                "devolucaoEmDias": 5
            };
            var val = {
                error: false
            };

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.cadastrarAluguel = jasmine.createSpy('cadastrarAluguel').and.returnValue(p);

            return vm.enviarDadosAluguel()
                .then(function () {
                    expect($location.path()).toEqual('/alugueis');
                })
        });

        it(': testando a funcao enviarDadosAluguel() com erro no retorno da service', function () {
            vm.form = {
                "clienteId": 1,
                "placaCarro": "ABC1234"
            };
            var val = {
                error: true,
                msg: "Mensagem"
            };

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.cadastrarAluguel = jasmine.createSpy('cadastrarAluguel').and.returnValue(p);

            helper.addAlerta = jasmine.createSpy('addAlerta');

            return vm.enviarDadosAluguel()
                .then(function () {
                    expect(helper.addAlerta).toHaveBeenCalled();
                })
        });

        it(': testando a funcao clientesSemAluguel()', function () {
            // Init
            vm.listaClientes = [];
            var val = [1, 2, 3];

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.listarClientesSemAluguel = jasmine.createSpy('listarClientesSemAluguel').and.returnValue(p);

            return vm.clientesSemAluguel()
                .then(function () {
                    expect(vm.listaClientes.length).toBeGreaterThan(0);
                });
        });

        it(': testando a funcao listarCarros()', function () {
            // Init
            vm.listaCarros = [];
            var val = [1, 2, 3];

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.listarCarrosDisponiveis = jasmine.createSpy('listarCarrosDisponiveis').and.returnValue(p);

            return vm.listarCarros()
                .then(function () {
                    expect(vm.listaCarros.length).toBeGreaterThan(0);
                });
        });

    });
});
