package it.unibas.bankrest.modello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BonificoInDTO {

    private Long id;
    @Min(0)
    private double importo;
    @NotBlank
    private String causale;
    @NotNull
    private Long contoA;
    @NotNull
    private Long contoB;
}
