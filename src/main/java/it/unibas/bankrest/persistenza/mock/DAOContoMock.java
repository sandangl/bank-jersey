

package it.unibas.bankrest.persistenza.mock;

import it.unibas.bankrest.modello.Conto;
import it.unibas.bankrest.persistenza.IDAOConto;

public class DAOContoMock extends DAOGenericoMock<Conto> implements IDAOConto{

    @Override
    public Conto findByIban(String iban) {
        for (Conto conto : this.findAll()) {
            if(conto.getIban().equalsIgnoreCase(iban)){
                return conto;
            }
        }
        return null;
    }

}
