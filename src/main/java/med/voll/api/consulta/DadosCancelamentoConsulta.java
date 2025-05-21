package med.voll.api.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Consulta consulta,
        @NotNull
        MotivoCancelamento motivo
) {
}
