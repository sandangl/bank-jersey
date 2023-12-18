package it.unibas.bankrest.modello.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BonificoOutDTO {

    private Long id;
    @FutureOrPresent
    private LocalDateTime data;
    @Min(0)
    private double importo;
    @NotBlank
    private String causale;
    @NotNull
    private Long contoA;
    @NotNull
    private Long contoB;
    
}
