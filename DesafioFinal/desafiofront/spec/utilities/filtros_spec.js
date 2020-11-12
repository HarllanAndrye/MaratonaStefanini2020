describe('Testando Filtros', function () {
    var $filter;

    beforeEach(module('autoLocadoraApp'));

    beforeEach(inject(function ($injector) {
        $filter = $injector.get('$filter');
    }));

    // INIT
    // ENTRADA
    // SAÍDA
    // MÉT0DO TESTADO
    it('$filter: deve estar DEFINIDO', function () {
        expect($filter).toBeDefined();
    });

    it(': deve colocar primeira letra maiuscula', function () {
        // MÉT0DO TESTADO
        expect($filter('capitalize')('teste')).toEqual('Teste');
    });

    it(': nao deve fazer nada para o filtro capitalize', function () {
        // Método testado
        expect($filter('capitalize')('')).toEqual('');
    });

    it(': deve colocar hifen na placa', function () {
        // MÉT0DO TESTADO
        expect($filter('placaComHifen')('TST1234')).toEqual('TST-1234');
    });

    it(': nao deve fazer nada para o filtro placaComHifen', function () {
        // Método testado
        expect($filter('placaComHifen')('')).toEqual('');
    });

});