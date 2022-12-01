package acc.br.controller;

import acc.br.dtos.UserDto;
import acc.br.entities.User;
import acc.br.exceptions.ExceptionHandler;
import acc.br.exceptions.UserNotFoundException;
import acc.br.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "Basic Auth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class UserController {

    @Inject
    private UserService userService;

    @GET
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Recupera usuarios", description = "Lista todos os usuários exitentes.")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))))
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GET
    @RolesAllowed({"USER", "ADMIN"})
    @Path("/{id}")
    @Operation(summary = "Recupera um usuario", description = "Recupera usuário pelo id.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description="Usuario não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public User getUser(@PathParam("id") Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @POST
    @PermitAll
    @Operation(summary = "Adiciona um usuario", description = "Cria um usuário e presiste no banco de dados")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))))
    public User createUser(@Valid UserDto userDto) {
        return userService.saveUser(userDto.toUser());
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Atualiza um usuario", description = "Atualiza um usuario existente pelo id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description="Usuario não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public User updateUser(@PathParam("id") int id, @Valid UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(id, userDto.toUser());
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Deleta um usuario", description = "Deleta um usuario pelo id")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Sucesso"),
            @APIResponse(responseCode = "404", description="Usuario não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}