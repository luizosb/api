package med.voll.api.medico.DTO;

import med.voll.api.endereco.DTO.EnderecoDTO;
import med.voll.api.medico.model.Especialidade;

public record MedicoDTO(String nome, String email, String telefone, String crm, Especialidade especialidade, EnderecoDTO enderecoDTO) {
}
