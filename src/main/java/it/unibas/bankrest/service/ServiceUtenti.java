package it.unibas.bankrest.service;

import it.unibas.bankrest.modello.Utente;
import it.unibas.bankrest.modello.dto.ContoDTO;
import it.unibas.bankrest.modello.dto.UtenteDTO;
import it.unibas.bankrest.persistenza.DAOFactory;
import it.unibas.bankrest.persistenza.IDAOUtente;
import it.unibas.bankrest.util.JWTUtil;
import it.unibas.bankrest.util.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServiceUtenti {

    IDAOUtente daoUtente = DAOFactory.getInstance().getDaoUtente();
    
    public String login(UtenteDTO utente) {
        Utente logging = daoUtente.findByUsername(utente.getUsername());
        if(logging == null){
            throw new IllegalArgumentException("Username inesistente.");
        }
        if(!logging.getPassword().equals(utente.getPassword())){
            throw new IllegalArgumentException("Password errata.");
        }
        return JWTUtil.generaToken(utente.getUsername());
    }

    public List<ContoDTO> getConti(String username) {
        Utente utente = daoUtente.findByUsername(username);
        if(utente == null){
            throw new IllegalArgumentException("Username inesistente.");
        }
        return Mapper.map(utente.getConti(), ContoDTO.class);
    }

}
