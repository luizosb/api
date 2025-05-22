package med.voll.api.consulta.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.consulta.model.MotivoCancelamento;
import med.voll.api.consulta.model.Consulta;

public record DadosCancelamentoConsulta(
        @NotNull
        Consulta consulta,
        @NotNull
        MotivoCancelamento motivo
) {
}
