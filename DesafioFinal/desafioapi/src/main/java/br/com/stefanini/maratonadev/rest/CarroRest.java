package br.com.stefanini.maratonadev.rest;

import br.com.stefanini.maratonadev.dto.CarroDto;
import br.com.stefanini.maratonadev.service.CarroService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("carro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroRest {

    @Inject
    CarroService service;

    @GET
    @Operation(summary = "Listar carros",
            description = "Lista de carros com ano de compra, marca,modelo e placa")
    @APIResponse(responseCode = "200",
            description = "carro",
            content = {
                    @Content(mediaType =  "application/json",
                            schema = @Schema(implementation = CarroDto.class))
            }
    )
    public Response listar(){
        return Response
                .status(Response.Status.OK)
                .entity(service.listar())
                .build();
    }
    
    @GET
	@Path("/{placa}")
	@Operation(summary = "Lista um carro",
		description = "Lista um carro.")
	@APIResponse(responseCode = "200",
		description = "lista um carro",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = CarroDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response listarPorPlaca(@PathParam("placa") String placa) {
		return Response
				.status(Status.OK)
				.entity(service.listarPorPlaca(placa))
				.build();
	}
    
    @GET
    @Path("disponiveis")
    @Operation(summary = "Listar carros disponíveis",
            description = "Lista de carros que estão disponíveis para locação.")
    @APIResponse(responseCode = "200",
            description = "carros disponíveis",
            content = {
                    @Content(mediaType =  "application/json",
                            schema = @Schema(implementation = CarroDto.class))
            }
    )
    public Response listarDisponiveis(){
        return Response
                .status(Response.Status.OK)
                .entity(service.listarDisponiveis())
                .build();
    }
    
    @POST
	@Operation(summary = "Incluir novo carro",
		description = "Incluir novo carro")
	@APIResponse(responseCode = "201",
		description = "incluir tarefa",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = CarroDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response incluir(CarroDto dto) {
    	List<String> errors = service.validateDto(dto); // Validando os dados do dto
    	
		if (!errors.isEmpty()) {
			String msg = "{"
					+ "\"error\": \"true\","
					+ "\"message\": \"" + errors.get(0) + "\""
							+ "}";
			
			//.entity("{\"error\": \"" + errors.get(0) + "\"}")
			return Response
					.status(Status.BAD_REQUEST)
					.entity(msg)
					.build();
		}
    	
		String retornoErro = service.incluir(dto);
		
		if (!retornoErro.isEmpty()) {
			String msg = "{"
					+ "\"error\": \"true\","
					+ "\"message\": \"" + retornoErro + "\""
							+ "}";
			
			return Response
					.status(Status.BAD_REQUEST)
					.entity(msg)
					.build();
		}
		
		//.entity("Carro cadastrado com sucesso.")
		return Response
				.status(Status.CREATED)
				.build();
	}
    
    @PUT
	@Path("/{placa}")
	@Operation(summary = "Atualizar informações de um carro",
		description = "Atualizar informações de um carro.")
	@APIResponse(responseCode = "201",
		description = "atualizar carro",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = CarroDto.class))
			}
	)
	public Response atualizar(@PathParam("placa") String placa, CarroDto dto) {
    	List<String> errors = service.validateDto(dto); // Validando os dados do dto
		
		if (!errors.isEmpty()) {
			return Response
					.status(Status.BAD_REQUEST)
					.entity("{\"error\": \"" + errors.get(0) + "\"}")
					.build();
		}
		
		service.atualizar(placa, dto);
		
		return Response
				.status(Status.CREATED)
				.build();
	}
    
    @DELETE
	@Path("/{placa}")
	@Operation(summary = "Excluir um carro",
		description = "Excluir um carro.")
	@APIResponse(responseCode = "204",
		description = "excluir carro",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = CarroDto.class))
			}
	)
	public Response deleteTodoList(@PathParam("placa") String placa) {
		if (service.excluir(placa)) {
			return Response
					.status(Status.NO_CONTENT)
					.build();
		}
		
		return Response
				.status(Status.INTERNAL_SERVER_ERROR)
				.build();
	}

}
