package it.unibas.bankrest.service;

import it.unibas.bankrest.modello.Carta;
import it.unibas.bankrest.modello.Conto;
import it.unibas.bankrest.modello.Utente;
import it.unibas.bankrest.modello.dto.CartaDTO;
import it.unibas.bankrest.persistenza.DAOFactory;
import it.unibas.bankrest.persistenza.IDAOCarta;
import it.unibas.bankrest.persistenza.IDAOConto;
import it.unibas.bankrest.persistenza.IDAOUtente;
import it.unibas.bankrest.util.Mapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceConti {

    private IDAOUtente daoUtente = DAOFactory.getInstance().getDaoUtente();
    private IDAOConto daoConto = DAOFactory.getInstance().getDaoConto();
    private IDAOCarta daoCarta = DAOFactory.getInstance().getDaoCarta();
    
    public long aggiungiCarta(String username, CartaDTO carta, Long idConto) {
        Utente utente = daoUtente.findByUsername(username);
        if(utente == null){
            throw new IllegalArgumentException("Username inesistente.");
        }
        Conto conto = daoConto.findById(idConto);
        if(conto == null){
            throw new IllegalArgumentException("Conto inesistente.");
        }
        if(utente.getConti().stream().noneMatch(c -> c.getId().equals(conto.getId()))){
            throw new IllegalArgumentException("Conto non associato all'utente.");
        }
        if(carta.getId() != null){
            throw new IllegalArgumentException("L'id non deve essere inizializzato.");           
        }
        Carta nuovaCarta = Mapper.map(carta, Carta.class);
        daoCarta.makePersistent(nuovaCarta);
        conto.setCarta(nuovaCarta);
        daoConto.makePersistent(conto);
        return nuovaCarta.getId();
    }
    
}
