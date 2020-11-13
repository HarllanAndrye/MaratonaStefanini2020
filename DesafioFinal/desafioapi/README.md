# Back-end

Este é o back-end do sistema de locação de veículos e esse projeto usa o Quarkus (Supersonic Subatomic Java Framework), Maven e o JDK 11.

## Comandos para a aplicação

Para executar o projeto em modo dev, use o comando (no cmd do Windows):
```shell script
mvnw quarkus:dev
```

Para executar os testes de unidade, use:
```shell script
mvnw clean test
```

Para fazer o build (packaging) do projeto, use:
```shell script
mvnw package
```
Esse comando irá produzir o arquivo `desafioapi-1.0.0-SNAPSHOT-runner.jar` dentro da pasta `/target`.

Agora a aplicação pode ser executada assim: `java -jar target/desafioapi-1.0.0-SNAPSHOT-runner.jar`.


Para saber mais sobre como construir aplicações com Quarkus e Maven, você pode acessar: https://quarkus.io/guides/maven-tooling.html


## Documentação da API

Para ver a documentação dos endpoints da API, construída pelo Swagger, você pode acessar (com a aplicação em execução): `http://localhost:8080/swagger-ui/`

Os endpoints iniciam com `http://localhost:8080/api/`.

