package med.voll.api.paciente.DTO;

import med.voll.api.endereco.model.Endereco;
import med.voll.api.paciente.model.Paciente;

public record PacientesDadosDetalhadoDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {

    public PacientesDadosDetalhadoDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
