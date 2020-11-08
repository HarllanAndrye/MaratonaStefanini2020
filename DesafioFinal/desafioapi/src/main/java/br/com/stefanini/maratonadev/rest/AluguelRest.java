package br.com.stefanini.maratonadev.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.stefanini.maratonadev.dto.AluguelDto;
import br.com.stefanini.maratonadev.dto.HistoricoAluguelDto;
import br.com.stefanini.maratonadev.service.AluguelService;

@Path("aluguel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AluguelRest {

	@Inject
	AluguelService service;
	
	
	@GET
	@Operation(summary = "Listar alugueis",
		description = "Listar alugueis")
	@APIResponse(responseCode = "200",
		description = "lista de alugueis",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = AluguelDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response listar() {
		return Response
				.status(Status.OK)
				.entity(service.listarAlugueis())
				.build();
	}
	
	@GET
	@Path("/cliente/{id}")
	@Operation(summary = "Listar alugueis do cliente",
		description = "Listar alugueis do cliente passando o ID como parâmetro")
	@APIResponse(responseCode = "200",
		description = "lista de alugueis do cliente",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = HistoricoAluguelDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response listarAlugueisCliente(@PathParam("id") Long id) {
		return Response
				.status(Status.OK)
				.entity(service.listarAlugueisCliente(id))
				.build();
	}
	
	@POST
	@Operation(summary = "Inserir aluguel",
		description = "Inserir aluguel.")
	@APIResponse(responseCode = "201",
		description = "inserir aluguel",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = AluguelDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response cadastrarCarroParaCliente(AluguelDto dto) {
		List<String> errors = service.validateDto(dto); // Validando os dados do dto
    	
		if (!errors.isEmpty()) {
			return Response
					.status(Status.BAD_REQUEST)
					.entity("{\"error\": \"" + errors.get(0) + "\"}")
					.build();
		}
		
		String retornoErro = service.cadastrarAluguel(dto);
		
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
		
		return Response
				.status(Status.CREATED)
				.build();
	}
	
	@PUT
	@Path("devolver")
	@Operation(summary = "Alterar aluguel",
		description = "Alterar aluguel.")
	@APIResponse(responseCode = "201",
		description = "alterar aluguel",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = AluguelDto.class))
			}
	)
	public Response devolverVeiculo(AluguelDto dto) {
		List<String> errors = service.validateDto(dto); // Validando os dados do dto
    	
		if (!errors.isEmpty()) {
			return Response
					.status(Status.BAD_REQUEST)
					.entity("{\"error\": \"" + errors.get(0) + "\"}")
					.build();
		}
		
		String retornoErro = service.devolverVeiculo(dto.getClienteId()); 
		
		if (!retornoErro.isEmpty()) {
			String msg = "{"
					+ "\"error\": \"true\","
					+ "\"message\": \"" + retornoErro + "\""
							+ "}";
			
			if (retornoErro.contains("não encontrado")) {
				return Response
						.status(Status.BAD_REQUEST)
						.entity(msg)
						.build();
			}
			
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity(msg)
					.build();
		}
		
		return Response
				.status(Status.CREATED)
				.build();
	}
	
}
