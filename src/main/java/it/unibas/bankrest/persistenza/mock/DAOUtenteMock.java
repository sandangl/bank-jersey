package it.unibas.bankrest.persistenza.mock;

import it.unibas.bankrest.modello.Utente;
import it.unibas.bankrest.persistenza.IDAOUtente;


public class DAOUtenteMock extends DAOGenericoMock<Utente> implements IDAOUtente {

    @Override
    public Utente findByUsername(String username) {
        for (Utente utente : this.findAll()) {
            if(utente.getUsername().equals(username)){
                return utente;
            }
        }
        return null;
    }

}
