describe('autoLocadoraService', function () {

    beforeEach(module('autoLocadoraApp'));

    describe('Testando Service', function () {
        var service, $httpBackend;

        var respSuccess = 'sucesso';
        /*var respSuccess = {
            "status": "OK",
            "data": {},
            "statusCode": 200
        };*/

        beforeEach(inject(function ($injector) {
            service = $injector.get('autoLocadoraService');
            $httpBackend = $injector.get('$httpBackend');
        }));

        beforeEach(function () {
            $httpBackend.whenGET('home.tpl.html').respond(200, {});
        });

        // INIT
        // ENTRADA
        // SAÍDA
        // MÉT0DO TESTADO
        it(': deve estar DEFINIDA', function () {
            expect(service).toBeDefined();
        });

        describe(': requisição para listar()', function () {
            var urlReq = 'http://localhost:8080/api/carro';
            var req = {};
            var method = 'GET';
            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // MÉT0DO TESTADO
                service.listar(req)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.listar(req, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para listarComPaginacao()', function () {
            var params = {
                "page": 1,
                "size": 12
            };

            var urlReq = 'http://localhost:8080/api/carro/' + params.page + '/' + params.size;
            var method = 'GET';
            
            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // MÉT0DO TESTADO
                service.listarComPaginacao(params)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.listarComPaginacao(params, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para listarCarrosDisponiveis()', function () {
            var urlReq = 'http://localhost:8080/api/carro/disponiveis';
            var req = {};
            var method = 'GET';
            
            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // MÉT0DO TESTADO
                service.listarCarrosDisponiveis(req)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.listarCarrosDisponiveis(req, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para getCarro()', function () {
            var placa = 'ABC1234';
            var urlReq = 'http://localhost:8080/api/carro/' + placa;
            var method = 'GET';
            
            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // MÉT0DO TESTADO
                service.getCarro(placa)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.getCarro(placa, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para cadastrarCarro()', function () {
            var params = {
                "ano": 2020,
                "marca": "Marca qualquer",
                "modelo": "Um modelo ai",
                "placa": "ABC1234"
            };
            var urlReq = 'http://localhost:8080/api/carro';
            var method = 'POST';
            
            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq, params)
                    .respond(201, respSuccess);
                
                // Método testado
                service.cadastrarCarro(params)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq, params)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.cadastrarCarro(params, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para cadastrarCliente()', function () {
            var params = {
                "contato": "(83)123456789",
                "cpf": "01234569874",
                "email": "email@email.com",
                "endereco": {
                  "bairro": "Bairro bom",
                  "cep": 58400000,
                  "cidade": "Campina Grande",
                  "complemento": "casa",
                  "logradouro": "Rua algum lugar",
                  "uf": "PB"
                },
                "nome": "Sou alguém na vida"
              };
            var urlReq = 'http://localhost:8080/api/cliente';
            var method = 'POST';
            
            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq, params)
                    .respond(201, respSuccess);
                
                // Método testado
                service.cadastrarCliente(params)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq, params)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.cadastrarCliente(params, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para listarClientes()', function () {
            var urlReq = 'http://localhost:8080/api/cliente';
            var req = {};
            var method = 'GET';

            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // MÉT0DO TESTADO
                service.listarClientes(req)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.listarClientes(req, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para listarClientesSemAluguel()', function () {
            var urlReq = 'http://localhost:8080/api/cliente/semAluguel';
            var req = {};
            var method = 'GET';

            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // MÉT0DO TESTADO
                service.listarClientesSemAluguel(req)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // MÉT0DO TESTADO
                service.listarClientesSemAluguel(req, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para getCliente()', function () {
            var id = 1;
            var urlReq = 'http://localhost:8080/api/cliente/' + id;
            var method = 'GET';

            it('deve dar sucesso', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // Método testado
                service.getCliente(id)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // INIT
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // Método testado
                service.getCliente(id, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para getAlugueisCliente()', function () {
            var id = 1;
            var urlReq = 'http://localhost:8080/api/aluguel/cliente/' + id;
            var method = 'GET';

            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // Método testado
                service.getAlugueisCliente(id)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // Init
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // Método testado
                service.getAlugueisCliente(id, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para cadastrarAluguel()', function () {
            var params = {
                "clienteId": 1,
                "devolucaoEmDias": 5,
                "placaCarro": "BQW0877"
              };
            var urlReq = 'http://localhost:8080/api/aluguel';
            var method = 'POST';
            
            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq, params)
                    .respond(201, respSuccess);
                
                // Método testado
                service.cadastrarAluguel(params)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // Init
                $httpBackend.when(method, urlReq, params)
                    .respond(400, {});
                // Método testado
                service.cadastrarAluguel(params, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para listarAlugueis()', function () {
            var urlReq = 'http://localhost:8080/api/aluguel';
            var req = {};
            var method = 'GET';

            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // Método testado
                service.listarAlugueis()
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // Init
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // Método testado
                service.listarAlugueis(req, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para devolverCarro()', function () {
            var params = {
                "clienteId": 1,
                "placaCarro": "BQW0877"
              };
            var urlReq = 'http://localhost:8080/api/aluguel/devolver';
            var method = 'PUT';
            
            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq, params)
                    .respond(201, respSuccess);
                
                // Método testado
                service.devolverCarro(params)
                    .then(function (response) {
                        expect(response).toEqual(201);
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // Init
                $httpBackend.when(method, urlReq, params)
                    .respond(400, {});
                // Método testado
                service.devolverCarro(params, 0);
                $httpBackend.flush();
            });
        });

        describe(': requisição para consultaCEP()', function () {
            var cep = '01001000';
            var urlReq = 'https://viacep.com.br/ws/' + cep + '/json/';
            var method = 'GET';

            it('deve dar sucesso', function () {
                // Init
                $httpBackend.when(method, urlReq)
                    .respond(200, respSuccess);
                // Método testado
                service.consultaCEP(cep)
                    .then(function (response) {
                        expect(response).toEqual('sucesso');
                    });
                $httpBackend.flush();
            });

            it('deve dar erro', function () {
                // Init
                $httpBackend.when(method, urlReq)
                    .respond(400, {});
                // Método testado
                service.consultaCEP(cep, 0);
                $httpBackend.flush();
            });
        });

    });
});
