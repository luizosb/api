package med.voll.api.paciente.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DTO.EnderecoDTO;

public record PacienteAtualizarDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
