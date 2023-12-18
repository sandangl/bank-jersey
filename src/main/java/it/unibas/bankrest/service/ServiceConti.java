package it.unibas.bankrest.service;

import it.unibas.bankrest.modello.Bonifico;
import it.unibas.bankrest.modello.Carta;
import it.unibas.bankrest.modello.Conto;
import it.unibas.bankrest.modello.Utente;
import it.unibas.bankrest.modello.dto.BonificoInDTO;
import it.unibas.bankrest.modello.dto.CartaDTO;
import it.unibas.bankrest.modello.dto.ModificaCartaDTO;
import it.unibas.bankrest.persistenza.DAOFactory;
import it.unibas.bankrest.persistenza.IDAOBonifico;
import it.unibas.bankrest.persistenza.IDAOCarta;
import it.unibas.bankrest.persistenza.IDAOConto;
import it.unibas.bankrest.persistenza.IDAOUtente;
import it.unibas.bankrest.util.Mapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ServiceConti {

    private IDAOUtente daoUtente = DAOFactory.getInstance().getDaoUtente();
    private IDAOConto daoConto = DAOFactory.getInstance().getDaoConto();
    private IDAOCarta daoCarta = DAOFactory.getInstance().getDaoCarta();
    private IDAOBonifico daoBonifico = DAOFactory.getInstance().getDaoBonifico();

    public long aggiungiCarta(String username, CartaDTO carta, Long idConto) {
        Utente utente = daoUtente.findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("Username inesistente.");
        }
        Conto conto = daoConto.findById(idConto);
        if (conto == null) {
            throw new IllegalArgumentException("Conto inesistente.");
        }
        if (utente.getConti().stream().noneMatch(c -> c.getId().equals(conto.getId()))) {
            throw new IllegalArgumentException("Conto non associato all'utente.");
        }
        if (conto.getCarta() != null) {
            throw new IllegalArgumentException("Al conto è già associata una carta.");
        }
        if (carta.getId() != null) {
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
        if (utente == null) {
            throw new IllegalArgumentException("Username inesistente.");
        }
        Conto conto = daoConto.findById(idConto);
        if (conto == null) {
            throw new IllegalArgumentException("Conto inesistente.");
        }
        if (utente.getConti().stream().noneMatch(c -> c.getId().equals(conto.getId()))) {
            throw new IllegalArgumentException("Conto non associato all'utente.");
        }
        Long idCarta = infoCarta.getId();
        if (idCarta == null) {
            throw new IllegalArgumentException("Specificare l'id della carta");
        }
        Carta carta = daoCarta.findById(idCarta);
        if (carta == null) {
            throw new IllegalArgumentException("Carta inesistente.");
        }
        if (utente.getConti().stream().noneMatch(c -> c.getCarta().getId().equals(idCarta))) {
            throw new IllegalArgumentException("Conto non associata al conto.");
        }
        if (!carta.getPin().equals(infoCarta.getVecchioPin())) {
            throw new IllegalArgumentException("Il PIN della carta è errato.");
        }
        if (carta.getPin().equals(infoCarta.getNuovoPin())) {
            log.warn("Il pin resta invariato, non lancio errore.");
        } else {
            carta.setPin(infoCarta.getNuovoPin());
            daoCarta.makePersistent(carta);
        }
    }

    public long effettuaBonifico(String username, LocalDateTime dataBonifico, BonificoInDTO bonifico, Long idConto) {
        Utente utente = daoUtente.findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("Username inesistente.");
        }
        Conto ordinante = daoConto.findById(idConto);
        if (ordinante == null) {
            throw new IllegalArgumentException("Conto selezionato inesistente.");
        }
        if (utente.getConti().stream().noneMatch(c -> c.getId().equals(ordinante.getId()))) {
            throw new IllegalArgumentException("Conto selezionato non associato all'utente.");
        }
        if (!bonifico.getContoA().equals(idConto)) {
            throw new IllegalArgumentException("L'id del conto selezionato non coincide con quello dell'ordinante.");
        }
        if (bonifico.getId() != null) {
            throw new IllegalArgumentException("L'id del bonifico non deve essere inizializzato.");
        }
        Conto beneficiario = daoConto.findById(bonifico.getContoB());
        if (beneficiario == null) {
            throw new IllegalArgumentException("Beneficiario inesistente.");
        }
        if (bonifico.getImporto() > ordinante.getSaldo()) {
            throw new IllegalArgumentException("L'importo del bonifico non può essere superiore a EUR " + ordinante.getSaldo());
        }
        if (dataBonifico.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)))
                && dataBonifico.toLocalDate().isEqual(LocalDate.now())) {
            dataBonifico = LocalDateTime.of(dataBonifico.toLocalDate().plusDays(1), LocalTime.of(8, 0));
        }
        Bonifico nuovoBonifico = Mapper.map(bonifico, Bonifico.class);
        nuovoBonifico.setData(dataBonifico);
        nuovoBonifico.setContoA(ordinante);
        nuovoBonifico.setContoB(beneficiario);
        daoBonifico.makePersistent(nuovoBonifico);
        ordinante.setSaldo(ordinante.getSaldo() - nuovoBonifico.getImporto());
        ordinante.getBonifici().add(nuovoBonifico);
        beneficiario.setSaldo(beneficiario.getSaldo() + nuovoBonifico.getImporto());
        beneficiario.getBonifici().add(nuovoBonifico);
        daoConto.makePersistent(ordinante);
        daoConto.makePersistent(beneficiario);
        return nuovoBonifico.getId();
    }
}
