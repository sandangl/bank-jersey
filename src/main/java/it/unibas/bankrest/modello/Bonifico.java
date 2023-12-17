package it.unibas.bankrest.modello;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Bonifico {

    private Long id;
    private LocalDateTime data;
    private double importo;
    private String causale;
    private Conto contoA;
    private Conto contoB;

    public Bonifico(LocalDateTime data, double importo, String causale, Conto contoA, Conto contoB) {
        this.data = data;
        this.importo = importo;
        this.causale = causale;
        this.contoA = contoA;
        this.contoB = contoB;
    }
    
    
    
}
