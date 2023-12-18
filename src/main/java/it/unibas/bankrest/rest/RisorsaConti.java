package it.unibas.bankrest.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.unibas.bankrest.modello.dto.BonificoInDTO;
import it.unibas.bankrest.modello.dto.CartaDTO;
import it.unibas.bankrest.modello.dto.ModificaCartaDTO;
import it.unibas.bankrest.service.ServiceConti;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import java.time.LocalDateTime;

@RequestScoped
@Path("/conti/")
@SecurityRequirement(name = "bearerAuth")
@Produces(MediaType.APPLICATION_JSON)
public class RisorsaConti {

    @Inject
    ServiceConti serviceConti;
    
    @Context
    SecurityContext securityContext;
    
    @POST
    @Path("/{idConto}/aggiungiCarta")
    @Consumes(MediaType.APPLICATION_JSON)
    public long aggiungiCarta(@NotNull @Valid CartaDTO carta,@NotNull @PathParam("idConto") Long idConto){
        return serviceConti.aggiungiCarta(securityContext.getUserPrincipal().getName(), carta, idConto);
    }
    
    @PUT
    @Path("/{idConto}/modificaPinCarta")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modificaPinCarta(@NotNull @Valid ModificaCartaDTO carta,@NotNull @PathParam("idConto") Long idConto){
        serviceConti.modificaPinCarta(securityContext.getUserPrincipal().getName(), carta, idConto);
    }
    
    @POST
    @Path("/{idConto}/effettuaBonifico")
    @Consumes(MediaType.APPLICATION_JSON)
    public long effettuaBonifico(@NotNull @Valid BonificoInDTO bonifico, @FutureOrPresent LocalDateTime data, @NotNull @PathParam("idConto") Long idConto){
        return serviceConti.effettuaBonifico(securityContext.getUserPrincipal().getName(), data, bonifico, idConto);
    }
}
