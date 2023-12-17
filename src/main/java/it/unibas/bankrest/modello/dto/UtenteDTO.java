package it.unibas.bankrest.modello.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UtenteDTO {

    @Email
    private String username;
    @Size(min = 5)
    private String password;
}
