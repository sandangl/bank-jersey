package it.unibas.bankrest.modello;

import lombok.Data;

@Data
public class Carta {

    private Long id;
    private String pin;
    private Conto conto;

    public Carta(String pin, Conto conto) {
        this.pin = pin;
        this.conto = conto;
    }
    
}
