package it.unibas.bankrest.modello;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Carta {

    private Long id;
    private String pin;
    private Conto conto;

    public Carta(String pin, Conto conto) {
        this.pin = pin;
        this.conto = conto;
    }
    
}
