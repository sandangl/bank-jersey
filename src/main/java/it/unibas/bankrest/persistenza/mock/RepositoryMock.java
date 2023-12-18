package it.unibas.bankrest.persistenza.mock;

import it.unibas.bankrest.modello.Bonifico;
import it.unibas.bankrest.modello.Carta;
import it.unibas.bankrest.modello.Conto;
import it.unibas.bankrest.modello.Utente;
import java.time.LocalDateTime;

public class RepositoryMock extends RepositoryGenericoMock {

    private static final RepositoryMock singleton = new RepositoryMock();

    private RepositoryMock() {
        
        Utente u1 = new Utente("emusk@x.com", "tesla", "Elon", "Musk");
        Conto c1 = new Conto("IT16B090295403", "Elon", "Musk", 20000);
        Conto c2 = new Conto("IT16B090295404", "Elon", "Musk", 500000);
        Carta ca1 = new Carta("12345", c1);
        ca1.setConto(c1);
        u1.getConti().add(c1);
        u1.getConti().add(c2);
        super.saveOrUpdate(c1);
        super.saveOrUpdate(c2);        
        super.saveOrUpdate(ca1);
        super.saveOrUpdate(u1);
        Utente u2 = new Utente("mzuck@meta.com", "threads", "Mark", "Zuckerberg");
        Conto c3 = new Conto("IT15A0045894503", "Mark", "Zuckerberg", 1000000);
        Bonifico b1 = new Bonifico(LocalDateTime.now(), 200, "Tie' comprati da mangiare.", c3, c1);
        c3.getBonifici().add(b1);
        c3.setSaldo(c3.getSaldo() - b1.getImporto());
        c1.getBonifici().add(b1);
        c1.setSaldo(c1.getSaldo() + b1.getImporto());
        u2.getConti().add(c3);
        super.saveOrUpdate(u2);
        super.saveOrUpdate(c3);
        super.saveOrUpdate(c1);
    }

    public static RepositoryMock getInstance() {
        return singleton;
    }

}
