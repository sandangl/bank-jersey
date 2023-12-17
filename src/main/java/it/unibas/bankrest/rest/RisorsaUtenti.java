package it.unibas.bankrest.rest;

import it.unibas.bankrest.modello.dto.UtenteDTO;
import it.unibas.bankrest.service.ServiceUtenti;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@RequestScoped
@Path("/utente")
@Produces(MediaType.APPLICATION_JSON)
public class RisorsaUtenti {

    @Inject
    private ServiceUtenti serviceUtenti;
        
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String login(@NotNull @Valid UtenteDTO utente){
        return serviceUtenti.login(utente);
    }
    
}
