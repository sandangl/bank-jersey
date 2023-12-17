package it.unibas.bankrest.persistenza;

import it.unibas.bankrest.modello.Utente;

/**
 *
 * @author seman
 */
public interface IDAOUtente extends IDAOGenerico<Utente>{

    public Utente findByUsername(String username);
    
}
