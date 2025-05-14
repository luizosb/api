package med.voll.api.paciente.DTO;

import med.voll.api.paciente.model.Paciente;

public record PacienteListagemDTO(
        String nome,
        String email,
        String cpf
) {
    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
