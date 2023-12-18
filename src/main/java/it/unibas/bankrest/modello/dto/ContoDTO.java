package it.unibas.bankrest.modello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContoDTO {

    private Long id;
    @NotBlank
    @Size(min=15, max=15)
    private String iban;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @Min(0)
    private double saldo;
}
