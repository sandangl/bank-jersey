package it.unibas.bankrest.modello.dto;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CartaDTO {

    private Long id;
    @Size(min=5, max=5)
    @Pattern(regexp = "[0-9]{5}")
    private String pin;
}
