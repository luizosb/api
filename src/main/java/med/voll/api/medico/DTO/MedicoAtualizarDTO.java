package med.voll.api.medico.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DTO.EnderecoDTO;

public record MedicoAtualizarDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
