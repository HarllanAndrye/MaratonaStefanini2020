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
public class AluguelRestTest {
	
	@Order(1)
	@Test
	public void listarTest() {
		given()
			.when()
			.get("/api/aluguel")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(is("[]")); // 1º GET vem vazio, pois não há aluguel.
	}
	
	@Order(2)
	@Test
	public void cadastrarCarroParaClienteTest() {
		String data = "{\r\n" + 
				"  \"clienteId\": 1,\r\n" + 
				"  \"devolucaoEmDias\": 5,\r\n" + 
				"  \"placaCarro\": \"BQW0877\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/aluguel")
			.then()
			.statusCode(201);
	}
	
	@Order(3)
	@Test
	public void cadastrarCarroParaClienteComErroTest() {
		String data = "{\r\n" + 
				"  \"clienteId\": 1,\r\n" + 
				"  \"devolucaoEmDias\": 5,\r\n" + 
				"  \"placaCarro\": \"\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/aluguel")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(4)
	@Test
	public void cadastrarCarroParaClienteComErro2Test() {
		String data = "{\r\n" + 
				"  \"clienteId\": 1,\r\n" + 
				"  \"devolucaoEmDias\": 5,\r\n" + 
				"  \"placaCarro\": \"BQW0877\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/aluguel")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("Cliente já possui carro alugado")));
	}
	
	@Order(5)
	@Test
	public void listarAlugueisClienteTest() {
		given()
			.when()
			.get("/api/aluguel/cliente/1")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(anyOf(containsString("BQW0877")));
	}
	
	@Order(6)
	@Test
	public void devolverVeiculoComErroTest() {
		String data = "{\r\n" + 
				"  \"clienteId\": 1,\r\n" + 
				"  \"placaCarro\": \"\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.put("/api/aluguel/devolver")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(7)
	@Test
	public void devolverVeiculoComErro2Test() {
		String data = "{\r\n" + 
				"  \"clienteId\": 11,\r\n" + 
				"  \"placaCarro\": \"BQW0877\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.put("/api/aluguel/devolver")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(8)
	@Test
	public void devolverVeiculoTest() {
		String data = "{\r\n" + 
				"  \"clienteId\": 1,\r\n" + 
				"  \"placaCarro\": \"BQW0877\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.put("/api/aluguel/devolver")
			.then()
			.statusCode(201);
	}

}
