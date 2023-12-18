package it.unibas.bankrest.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.unibas.bankrest.modello.dto.BonificoOutDTO;
import it.unibas.bankrest.modello.dto.ContoDTO;
import it.unibas.bankrest.modello.dto.UtenteDTO;
import it.unibas.bankrest.service.ServiceUtenti;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;

@RequestScoped
@Path("/utente")
@Produces(MediaType.APPLICATION_JSON)
public class RisorsaUtenti {

    @Inject
    private ServiceUtenti serviceUtenti;
    
    @Context
    private SecurityContext securityContext;
        
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String login(@NotNull @Valid UtenteDTO utente){
        return serviceUtenti.login(utente);
    }
    
    @GET
    @Path("/me")
    @SecurityRequirement(name="bearerAuth")
    public List<ContoDTO> getConti(){
        return serviceUtenti.getConti(securityContext.getUserPrincipal().getName());
    }
    
    @GET
    @Path("/me/{idConto}/bonifici")
    @SecurityRequirement(name="bearerAuth")
    public List<BonificoOutDTO> getBonifici(@NotNull @PathParam("idConto") Long idConto){
        return serviceUtenti.getBonifici(securityContext.getUserPrincipal().getName(), idConto);
    }
    
    @POST
    @Path("/me/apriConto")
    @Consumes(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name="bearerAuth")
    public long apriConto(@NotNull @Valid ContoDTO conto){
        return serviceUtenti.apriConto(conto, securityContext.getUserPrincipal().getName());
    }
    
}
