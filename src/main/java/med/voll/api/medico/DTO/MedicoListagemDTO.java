package med.voll.api.medico.DTO;

import med.voll.api.medico.model.Especialidade;
import med.voll.api.medico.model.Medico;

public record MedicoListagemDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public MedicoListagemDTO (Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
