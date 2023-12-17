package it.unibas.bankrest.persistenza;

import it.unibas.bankrest.modello.Configurazione;

import static it.unibas.bankrest.enums.EStrategiaPersistenza.DB_HIBERNATE;
import it.unibas.bankrest.persistenza.mock.DAOBonificoMock;
import it.unibas.bankrest.persistenza.mock.DAOCartaMock;
import it.unibas.bankrest.persistenza.mock.DAOContoMock;
import it.unibas.bankrest.persistenza.mock.DAOUtenteMock;

public class DAOFactory {

    private static final DAOFactory singleton = new DAOFactory();
    private IDAOUtente daoUtente;
    private IDAOConto daoConto;
    private IDAOBonifico daoBonifico;
    private IDAOCarta daoCarta;

    public static DAOFactory getInstance() {
        return singleton;
    }

    public IDAOUtente getDaoUtente() {
        return daoUtente;
    }

    public IDAOConto getDaoConto() {
        return daoConto;
    }

    public IDAOBonifico getDaoBonifico() {
        return daoBonifico;
    }

    public IDAOCarta getDaoCarta() {
        return daoCarta;
    }

    private DAOFactory() {
        if (Configurazione.getInstance().getStrategiaDb().equals(DB_HIBERNATE)) {

        } else {
            daoUtente = new DAOUtenteMock();
            daoConto = new DAOContoMock();
            daoBonifico = new DAOBonificoMock();
            daoCarta = new DAOCartaMock();

        }
    }

}
