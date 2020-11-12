describe('ClienteController', function () {

    var $controller, $rootScope, $httpBackend, $location, service, helper;

    beforeEach(function () {
        angular.mock.autoLocadoraMockApp();
    });

    beforeEach(inject(function ($injector) {
        $controller = $injector.get('$controller');
        $rootScope = $injector.get('$rootScope');
        $httpBackend = $injector.get('$httpBackend');
        $location = $injector.get('$location');
        service = $injector.get('autoLocadoraService');
        helper = $injector.get('helperFactory');
    }));

    afterEach(function () {
        $httpBackend.flush();

        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    beforeEach(function () {
        $httpBackend.whenGET('home.tpl.html').respond(200, {});
        $httpBackend.whenGET('assets/estados.json').respond(200, {});
        $httpBackend.whenGET('pages/clientes/clientes.tpl.html').respond(200, {});
    });

    describe('Testando Controller', function () {
        var vm, scope;

        beforeEach(function () {
            scope = $rootScope.$new();
            vm = $controller('ClienteController', { '$rootScope': $rootScope, '$scope': scope });
        });
        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': testando a funcao enviarDadosCliente() em caso de sucesso', function () {
            // Init
            var val = {
                "status": 200
            };

            $location.path('/cliente');

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.cadastrarCliente = jasmine.createSpy('cadastrarCliente').and.returnValue(p);

            helper.addAlerta = jasmine.createSpy('addAlerta');
            
            // Método testado
            return vm.enviarDadosCliente()
                .then(function () {
                    expect($location.path()).toEqual('/clientes');
                });
        });

        it(': testando a funcao enviarDadosCliente() em caso de erro', function () {
            // Init
            var val = {
                "status": 400,
                "data": {
                    "error": true
                }
            };

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.cadastrarCliente = jasmine.createSpy('cadastrarCliente').and.returnValue(p);

            helper.addAlerta = jasmine.createSpy('addAlerta');
            
            // Método testado
            return vm.enviarDadosCliente()
                .then(function () {
                    expect(helper.addAlerta).toHaveBeenCalled();
                });
        });

        it(': testando a funcao consultaCEP()', function () {
            // Init
            vm.form = {
                "endereco": {
                    "cep": "01001000"
                }
            };

            var val = {
                "cep": "01001-000",
                "logradouro": "Praça da Sé",
                "complemento": "lado ímpar",
                "bairro": "Sé",
                "localidade": "São Paulo",
                "uf": "SP",
                "ibge": "3550308",
                "gia": "1004",
                "ddd": "11",
                "siafi": "7107"
              };

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.consultaCEP = jasmine.createSpy('consultaCEP').and.returnValue(p);

            // Método testado
            return vm.consultaCEP()
                .then(function () {
                    expect(vm.form.endereco.logradouro).toEqual('Praça da Sé');
                });
        });

        it(': testando a funcao consultaCEP() sem passar dados', function () {
            // Init
            var val = {
                "cep": "01001-000",
                "logradouro": "Praça da Sé"
              };

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.consultaCEP = jasmine.createSpy('consultaCEP').and.returnValue(p);

            // Método testado
            vm.consultaCEP();
            
            expect(vm.form).toBeUndefined();
        });

    });
});
