package br.com.stefanini.maratonadev.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class ClienteRestTest {

	@Order(1)
	@Test
	public void listarClientesTest() {
		given()
			.when()
			.get("/api/cliente")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(is("[]")); // 1º GET vem vazio, pois não cliente cadastrado.
	}
	
	@Order(2)
	@Test
	public void cadastrarTest() {
		String data = "{\r\n" + 
				"  \"contato\": \"(83)123456789\",\r\n" + 
				"  \"cpf\": \"01234569874\",\r\n" + 
				"  \"email\": \"email@email.com\",\r\n" + 
				"  \"endereco\": {\r\n" + 
				"    \"bairro\": \"Bairro bom\",\r\n" + 
				"    \"cep\": 58400000,\r\n" + 
				"    \"cidade\": \"Campina Grande\",\r\n" + 
				"    \"complemento\": \"casa\",\r\n" + 
				"    \"logradouro\": \"Rua algum lugar\",\r\n" + 
				"    \"uf\": \"PB\"\r\n" + 
				"  },\r\n" + 
				"  \"nome\": \"Nome teste\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/cliente")
			.then()
			.statusCode(201)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("Nome teste")));
	}
	
	@Order(3)
	@Test
	public void listarClientesSemAluguelTest() {
		given()
			.when()
			.get("/api/cliente/semAluguel")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(anyOf(containsString("01234569874")));
	}
	
	@Order(4)
	@Test
	public void listarClientePorIdTest() {
		given()
			.when()
			.get("/api/cliente/1")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(anyOf(containsString("01234569874")));
	}
	
	@Order(5)
	@Test
	public void cadastrarComErroTest() {
		String data = "{\r\n" + 
				"  \"contato\": \"(83)123456789\",\r\n" + 
				"  \"cpf\": \"01234569874\",\r\n" + 
				"  \"email\": \"email@email.com\",\r\n" + 
				"  \"endereco\": {\r\n" + 
				"    \"bairro\": \"Bairro bom\",\r\n" + 
				"    \"cep\": 58400000,\r\n" + 
				"    \"cidade\": \"Campina Grande\",\r\n" + 
				"    \"complemento\": \"casa\",\r\n" + 
				"    \"logradouro\": \"Rua algum lugar\",\r\n" + 
				"    \"uf\": \"PB\"\r\n" + 
				"  },\r\n" + 
				"  \"nome\": \"\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/cliente")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(6)
	@Test
	public void cadastrarComErro2Test() {
		String data = "{\r\n" + 
				"  \"contato\": \"(83)123456789\",\r\n" + 
				"  \"cpf\": \"01234569874\",\r\n" + 
				"  \"email\": \"email@email.com\",\r\n" + 
				"  \"endereco\": {\r\n" + 
				"    \"bairro\": \"Bairro bom\",\r\n" + 
				"    \"cep\": 58400000,\r\n" + 
				"    \"cidade\": \"Campina Grande\",\r\n" + 
				"    \"complemento\": \"casa\",\r\n" + 
				"    \"logradouro\": \"Rua algum lugar\",\r\n" + 
				"    \"uf\": \"PB\"\r\n" + 
				"  },\r\n" + 
				"  \"nome\": \"Testando\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/cliente")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
}
