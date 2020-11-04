package br.com.stefanini.maratonadev.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.stefanini.maratonadev.dto.ClienteDto;
import br.com.stefanini.maratonadev.service.ClienteService;

@Path("cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteRest {
	
	@Inject
	ClienteService service;
	
	
	@GET
	@Operation(summary = "Listar clientes",
		description = "Listar clientes")
	@APIResponse(responseCode = "200",
		description = "lista de clientes",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = ClienteDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response listarClientes() {
		return Response
				.status(Status.OK)
				.entity(service.listarClientes())
				.build();
	}
	
	@GET
	@Path("{id}")
	@Operation(summary = "Listar cliente por Id",
		description = "Listar cliente por ID")
	@APIResponse(responseCode = "200",
		description = "listar cliente",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = ClienteDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response listarClientePorId(@PathParam("id") Long id) {
		return Response
				.status(Status.OK)
				.entity(service.listarPorId(id))
				.build();
	}

	
	@POST
	@Operation(summary = "Inserir cliente",
		description = "Inserir cliente.")
	@APIResponse(responseCode = "201",
		description = "inserir cliente",
		content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = ClienteDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response cadastrar(ClienteDto dto) {
		List<String> errors = service.validateDto(dto); // Validando os dados do dto
    	
		if (!errors.isEmpty()) {
			return Response
					.status(Status.BAD_REQUEST)
					.entity("{\"error\": \"" + errors.get(0) + "\"}")
					.build();
		}
		
		Long id = service.cadastrar(dto);
		
		if (id == 0) {
			return Response
					.status(Status.BAD_REQUEST)
					.entity("{\"error\": \"CPF já cadastrado!\"}")
					.build();
		}
		
		return Response
				.status(Status.CREATED)
				.entity(service.listarPorId(id))
				.build();
	}
	
}
