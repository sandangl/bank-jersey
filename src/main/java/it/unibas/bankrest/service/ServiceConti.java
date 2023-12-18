package it.unibas.bankrest.service;

import it.unibas.bankrest.modello.Carta;
import it.unibas.bankrest.modello.Conto;
import it.unibas.bankrest.modello.Utente;
import it.unibas.bankrest.modello.dto.CartaDTO;
import it.unibas.bankrest.modello.dto.ModificaCartaDTO;
import it.unibas.bankrest.persistenza.DAOFactory;
import it.unibas.bankrest.persistenza.IDAOCarta;
import it.unibas.bankrest.persistenza.IDAOConto;
import it.unibas.bankrest.persistenza.IDAOUtente;
import it.unibas.bankrest.util.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
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
        if(conto.getCarta() != null){
            throw new IllegalArgumentException("Al conto è già associata una carta.");
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

    public void modificaPinCarta(String username, ModificaCartaDTO infoCarta, Long idConto) {
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
        Long idCarta = infoCarta.getId();
        if(idCarta == null){
            throw new IllegalArgumentException("Specificare l'id della carta");          
        }
        Carta carta = daoCarta.findById(idCarta);
        if(carta == null){
            throw new IllegalArgumentException("Carta inesistente.");
        }
        if(utente.getConti().stream().noneMatch(c -> c.getCarta().getId().equals(idCarta))){
            throw new IllegalArgumentException("Conto non associata al conto.");
        }
        if(!carta.getPin().equals(infoCarta.getVecchioPin())){
            throw new IllegalArgumentException("Il PIN della carta è errato.");            
        }
        if(carta.getPin().equals(infoCarta.getNuovoPin())){
            log.warn("Il pin resta invariato, non lancio errore.");
        }
        else{
            carta.setPin(infoCarta.getNuovoPin());
            daoCarta.makePersistent(carta);   
        }
    }
    
}
