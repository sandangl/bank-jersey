package it.unibas.bankrest.persistenza;

import it.unibas.bankrest.modello.Conto;

/**
 *
 * @author seman
 */
public interface IDAOConto extends IDAOGenerico<Conto>{

    public Conto findByIban(String iban);
    
}
