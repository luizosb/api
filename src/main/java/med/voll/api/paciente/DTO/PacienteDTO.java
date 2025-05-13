package med.voll.api.paciente.DTO;

import med.voll.api.endereco.DTO.EnderecoDTO;

public record PacienteDTO(String nome,
                          String email,
                          String telefone,
                          String cpf,
                          EnderecoDTO enderecoDTO) {
}
