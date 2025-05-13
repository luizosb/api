package med.voll.api.medico.DTO;

import med.voll.api.endereco.DTO.EnderecoDTO;
import med.voll.api.medico.model.Especialidade;

/**
 *Os nomes nos DTO tem que ser igual ao que está sendo mandado no JSON. Cammel case
 */
public record MedicoDTO(
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        EnderecoDTO endereco
) {
}
