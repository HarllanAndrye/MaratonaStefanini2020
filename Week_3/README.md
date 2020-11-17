# Semana 3 - Quarkus

> 19/10 à 24/10: Quarkus, Docker, Kubernetes e Banco de Dados.


:file_folder: todolist: Projeto que consiste em um serviço REST usando os métodos HTTP (GET, POST, PUT e DELETE).
Este projeto é um "To Do List", onde há tarefas com status TODO, DOING, DONE e BLOCK. Pode ser utilizado com um front-end tipo o Trello.

:paperclip: O projeto completo (front e back-end) pode ser encontrado [aqui](https://github.com/HarllanAndrye/ToDoList). :+1:

---

Assuntos tratados na semana:
* Serviços REST;
* Quarkus;
* Docker;
* Kubernetes;
* Banco de dados:
	* JPA - Java Persistence API
	* JDBC H2 e Hibernate ORM
	* [Panache](https://quarkus.io/guides/hibernate-orm-panache)
* Swagger (OpenAPI): Usado para gerar uma documentação da API;
	* End-point localhost:8080/swagger-ui
* [OpenTracing](https://quarkus.io/guides/opentracing) e [Jaeger](https://www.jaegertracing.io/docs/1.20/getting-started/);
* Autenticação (quarkus authentication).
	* [Quarkus - Security Architecture and guides](https://quarkus.io/guides/security)



Fluxo da API:

	Rest -> Service -> DAO


Rodar o projeto no terminal

	(produção) ./mvnw quarkus:dev

	(teste) ./mvnw quarkus:dev -Dquarkus.profile=test

