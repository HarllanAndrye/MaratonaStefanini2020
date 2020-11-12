describe('helperFactory', function () {

    // beforeEach(function () {
    //     angular.mock.listaComprasMock();
    // });
    beforeEach(module('autoLocadoraApp'));

    describe('Testando Factory', function () {
        var factory, $httpBackend, $rootScope, $location, $q;

        beforeEach(inject(function ($injector) {
            factory = $injector.get('helperFactory');
            $httpBackend = $injector.get('$httpBackend');
            $rootScope = $injector.get('$rootScope');
            $location = $injector.get('$location');
            $q = $injector.get('$q');
        }));

        beforeEach(function () {
            $httpBackend.whenGET('home.tpl.html').respond(200, {});
        });

        // INIT
        // ENTRADA
        // SAÍDA
        // MÉT0DO TESTADO
        it(': deve estar DEFINIDA', function () {
            expect(factory).toBeDefined();
        });

        it(': testando funcao sendError()', function () {
            // ENTRADA
            var ent = { data: { message: 'teste' } };
            // SAÍDA
            var ret = { error: true, msg: 'teste' };
            // MÉT0DO TESTADO
            expect(factory.sendError(ent)).toEqual(ret);
        });

        it(': testando funcao addAlerta()', function () {
            // Init
            $rootScope.alertas = [];

            // Entrada
            var msg = "Mensagem";
            var tipo = "warning";
            var acao = "acao";
            
            // Método testado
            factory.addAlerta(msg, tipo, acao);

            expect($rootScope.alertas[0].tipo).toEqual('warning');
        });

        it(': testando funcao addAlerta() com mensagem no rootScope', function () {
            // Init
            $rootScope.alertas = ["Mensagem"];

            // Entrada
            var msg = "Mensagem";
            var tipo = "";
            var acao = "";
            
            // Método testado
            factory.addAlerta(msg, tipo, acao);

            expect($rootScope.alertas[0].tipo).toBeUndefined();
        });

        it(': testando funcao addAlerta() sem mensagem', function () {
            // Init
            $rootScope.alertas = ["Mensagem"];

            // Entrada
            var msg = "";
            var tipo = "";
            var acao = "";
            
            // Método testado
            factory.addAlerta(msg, tipo, acao);

            expect($rootScope.alertas[0]).toEqual('Mensagem');

            // Segundo teste
            $rootScope.alertas = [];
            factory.addAlerta(msg, tipo, acao);
            expect($rootScope.alertas[0]).toBeUndefined();
        });

        it(': testando funcao setRootScope()', function () {
            // Init
            $rootScope["chave"] = [];

            // Entrada
            var key = "chave";
            var obj = "objeto";
            
            // Método testado
            factory.setRootScope(key, obj);

            expect($rootScope[key]).toEqual(obj);
        });

        it(': testando funcao getRootScope()', function () {
            // Init
            $rootScope["chave"] = "objeto";

            // Entrada
            var key = "chave";
            
            // Método testado
            expect(factory.getRootScope(key)).toEqual("objeto");
        });

        it(': testando funcao rootScopeApply()', function () {
            /**
             * https://docs.angularjs.org/api/ng/service/$q#testing
             */
            var deferred = $q.defer();
            var promise = deferred.promise;
            var resolvedValue;

            promise.then(function(value) { resolvedValue = value; });
            expect(resolvedValue).toBeUndefined();

            // Simulate resolving of promise
            deferred.resolve(123);

            // Note that the 'then' function does not get called synchronously.
            // This is because we want the promise API to always be async, whether or not
            // it got called synchronously or asynchronously.
            expect(resolvedValue).toBeUndefined();

            // Método testado
            factory.rootScopeApply();

            expect(resolvedValue).toEqual(123);
        });

        it(': testando funcao path()', function () {
            // Init
            $location.path("/clientes");
            $rootScope.alertas = "alertas";

            // Entrada
            var path = "/home";
            
            // Método testado
            factory.path(path);

            expect($rootScope.alertas.length).toEqual(0);
            expect($location.path()).toEqual("/home");

            $location.path("/clientes");
            // Método testado (sem path)
            factory.path("");
            expect($location.path()).toEqual("/clientes");
        });

        it(': testando funcao go() sem path', function () {
            // Init
            $location.path("/clientes");

            // Método testado (sem path)
            factory.go("");
            expect($location.path()).toEqual("/clientes");
        });

    });
});
