package it.unibas.bankrest.modello;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Utente {

    private Long id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private List<Conto> conti = new ArrayList<>();

    public Utente(String username, String password, String nome, String cognome) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }
    
}
