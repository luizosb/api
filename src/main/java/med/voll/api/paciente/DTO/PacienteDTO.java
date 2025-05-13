package med.voll.api.paciente.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DTO.EnderecoDTO;
import org.hibernate.validator.constraints.br.CPF;

public record PacienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        EnderecoDTO endereco) {
}
