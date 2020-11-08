package br.com.stefanini.maratonadev.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.stefanini.maratonadev.model.Carro;
import br.com.stefanini.maratonadev.model.parser.CarroParser;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;


@QuarkusTest
public class CarroServiceTest {

	@Inject
	CarroService service;
	
	static List<Carro> carros = new ArrayList<>();
	
	@BeforeAll
	public static void mockCarro() {
		Carro car = new Carro();
		car.setAno(2020);
		car.setMarca("FIAT");
		car.setModelo("Um modelo");
		car.setPlaca("ABC1234");
		
		carros.add(car);
		
		CarroService mock = Mockito.mock(CarroService.class);
		Mockito.when(mock.listar()).thenReturn(carros.stream().map(CarroParser.get()::dto).collect(Collectors.toList()));
		Mockito.when(mock.listarDisponiveis()).thenReturn(carros.stream().map(CarroParser.get()::dto).collect(Collectors.toList()));
		
		QuarkusMock.installMockForType(mock, CarroService.class);
	}
	
	@Test
	public void listarTest() {
		assertThat(service.listar(), containsInAnyOrder(
                hasProperty("placa", is("ABC1234"))
        ));
	}
	
	@Test
	public void listarDisponiveisTest() {
		assertThat(service.listarDisponiveis(), containsInAnyOrder(
                hasProperty("placa", is("ABC1234"))
        ));
	}
	
}
