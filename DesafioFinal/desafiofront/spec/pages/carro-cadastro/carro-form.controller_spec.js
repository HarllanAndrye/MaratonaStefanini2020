describe('CarroController', function () {

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
        $httpBackend.whenGET('pages/carro-cadastro/carro-form.tpl.html').respond(200, {});
    });

    describe('Testando Controller', function () {
        var vm, scope;

        beforeEach(function () {
            scope = $rootScope.$new();
            vm = $controller('CarroController', { '$rootScope': $rootScope, '$scope': scope });

        });
        // ENTRADA
        // SAÍDA
        // INIT
        // MÉT0DO TESTADO
        it(': deve estar definido', function () {
            expect(vm).toBeDefined();
        });

        it(': testando a funcao cadastrar() redirecionando a pagina', function () {
            // Init
            var val = 201;
            $location.path('/carro/cadastro');

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.cadastrarCarro = jasmine.createSpy('cadastrarCarro').and.returnValue(p);
            
            // Método testado
            return vm.cadastrar()
                .then(function () {
                    expect($location.path()).toEqual('/home');
                });
        });

        it(': testando a funcao cadastrar() quando ocorre erro', function () {
            // Init
            var val = {
                "error": true,
                "msg": "Ocorreu Erro!"
            };

            var p = new Promise(function (resolve, reject) { resolve(val); });
            service.cadastrarCarro = jasmine.createSpy('cadastrarCarro').and.returnValue(p);

            helper.addAlerta = jasmine.createSpy('addAlerta');
            
            // Método testado
            return vm.cadastrar()
                .then(function () {
                    expect(helper.addAlerta).toHaveBeenCalled();
                });
        });

    });
});
