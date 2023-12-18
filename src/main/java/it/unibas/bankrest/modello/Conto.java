package it.unibas.bankrest.modello;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Conto {
    
    private Long id;
    private String iban;
    private String nome;
    private String cognome;
    private double saldo;
    private Carta carta;
    private List<Bonifico> bonifici  = new ArrayList<>();
    
    public Conto(String iban, String nome, String cognome, double saldo) {
        this.iban = iban;
        this.nome = nome;
        this.cognome = cognome;
        this.saldo = saldo;
    }
    
}
