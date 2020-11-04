package br.com.stefanini.maratonadev.rest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class CarroRestTest {
	
	@Order(1)
	@Test
	public void getTest() {
		/*
		given()
			.auth()
			.preemptive()
			.basic("teste@email.com", "123456")
			.when()
			.get("/restapi/todolist")
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body(is("[]")); // 1º GET vem vazio no banco de teste
		*/
	}

}
