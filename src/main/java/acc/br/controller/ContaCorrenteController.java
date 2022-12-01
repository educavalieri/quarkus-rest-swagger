package acc.br.controller;

import acc.br.dtos.ContaCorrenteDto;
import acc.br.dtos.UserDto;
import acc.br.entities.ContaCorrente;
import acc.br.entities.User;
import acc.br.exceptions.ExceptionHandler;
import acc.br.exceptions.UserNotFoundException;
import acc.br.service.ContaCorrenteService;
import acc.br.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/v1/conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContaCorrenteController {

    @Inject
    private ContaCorrenteService contaCorrenteService;

    @Inject
    private UserService userService;

    @Operation(summary = "Recupera Contas", description = "Lista todos as contas existentes.")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContaCorrente.class))))
    public List<ContaCorrente> getContas() {
        return contaCorrenteService.getAllContas();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Recupera uma conta", description = "Recupera conta pelo id.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContaCorrente.class))),
            @APIResponse(responseCode = "404", description="Conta não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public ContaCorrente getConta(@PathParam("id") Long id) throws UserNotFoundException {
        return contaCorrenteService.getContaById(id);
    }

    @POST
    @Operation(summary = "Adiciona uma conta", description = "Cria uma conta e presiste no banco de dados")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContaCorrente.class))))
    public ContaCorrente createConta(ContaCorrenteDto contaCorrenteDto) throws UserNotFoundException {
        ContaCorrente contaCorrente = new ContaCorrente();
        var user = userService.getUserById(contaCorrenteDto.getUser_id());
        contaCorrente.setUser(user);
        contaCorrente.setAgencia(contaCorrenteDto.getAgencia());
        contaCorrente.setNumero(contaCorrenteDto.getNumero());
        contaCorrente.setSaldo(contaCorrenteDto.getSaldo());

        return contaCorrente;
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deleta uma conta", description = "Deleta uma conta pelo id")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Sucesso"),
            @APIResponse(responseCode = "404", description="Conta não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Response deleteConta(@PathParam("id") int id) throws UserNotFoundException {
        contaCorrenteService.deleteConta(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }

}
