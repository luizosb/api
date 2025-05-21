package med.voll.api.consulta;

import lombok.Getter;

@Getter
public enum MotivoCancelamento {

    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Médico cancelou"),
    OUTROS("Outros");

    private final String motivo;

    MotivoCancelamento(String motivo) {
        this.motivo = motivo;
    }
}
