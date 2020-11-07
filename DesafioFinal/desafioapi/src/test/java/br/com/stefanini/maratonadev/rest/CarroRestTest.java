package br.com.stefanini.maratonadev.rest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;

import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class CarroRestTest {
	
	@Order(1)
	@Test
	public void listarTest() {
		given()
			.when()
			.get("/api/carro")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(anyOf(containsString("placa")));
	}
	
	@Order(2)
	@Test
	public void listarPorPlacaTest() {
		String returnIs = "{\"ano\":1990,\"marca\":\"GM - Chevrolet\",\"modelo\":\"SONIC Sed. LTZ 1.6 16V FlexPower 4p Aut.\",\"placa\":\"BQW0877\"}";
		
		given()
			.when()
			.get("/api/carro/BQW0877")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(is(returnIs)); // anyOf(containsString("GM - Chevrolet"))
	}
	
	@Order(3)
	@Test
	public void listarDisponiveisTest() {
		given()
			.when()
			.get("/api/carro/disponiveis")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(anyOf(containsString("placa")));
	}
	
	@Order(4)
	@Test
	public void incluirTest() {
		String data = "{\r\n" + 
				"  \"ano\": 2020,\r\n" + 
				"  \"marca\": \"Marca qualquer\",\r\n" + 
				"  \"modelo\": \"Um modelo ai\",\r\n" + 
				"  \"placa\": \"ABC1234\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/carro")
			.then()
			.statusCode(201);
	}
	
	@Order(5)
	@Test
	public void incluirComErroTest() {
		String data = "{\r\n" + 
				"  \"ano\": 2020,\r\n" + 
				"  \"marca\": \"Marca qualquer\",\r\n" + 
				"  \"modelo\": \"Um modelo ai\",\r\n" + 
				"  \"placa\": \"\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/carro")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(6)
	@Test
	public void incluirComErro2Test() {
		String data = "{\r\n" + 
				"  \"ano\": 2020,\r\n" + 
				"  \"marca\": \"Marca qualquer\",\r\n" + 
				"  \"modelo\": \"Um modelo ai\",\r\n" + 
				"  \"placa\": \"ABC1234\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.post("/api/carro")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(7)
	@Test
	public void atualizarTest() {
		String data = "{\r\n" + 
				"  \"ano\": 2020,\r\n" + 
				"  \"marca\": \"Marca qualquer alterada\",\r\n" + 
				"  \"modelo\": \"Um modelo ai\",\r\n" + 
				"  \"placa\": \"ABC1234\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.put("/api/carro/ABC1234")
			.then()
			.statusCode(201);
	}
	
	@Order(8)
	@Test
	public void atualizarComErroTest() {
		String data = "{\r\n" + 
				"  \"ano\": 2020,\r\n" + 
				"  \"marca\": \"Marca qualquer alterada\",\r\n" + 
				"  \"modelo\": \"Um modelo ai\",\r\n" + 
				"  \"placa\": \"\"\r\n" + 
				"}";
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.when()
			.put("/api/carro/ABC1234")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}
	
	@Order(9)
	@Test
	public void excluirCarroTest() {		
		given()
			.when()
			.delete("/api/carro/ABC1234")
			.then()
			.statusCode(204);
	}
	
	@Order(10)
	@Test
	public void excluirCarroComErroTest() {		
		given()
			.when()
			.delete("/api/carro/ABC4321")
			.then()
			.statusCode(400)
			.contentType(ContentType.JSON)
			.body(anyOf(containsString("error")));
	}

}
