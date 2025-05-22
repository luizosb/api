package med.voll.api.consulta.validacoes;

import med.voll.api.consulta.DTO.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoPacienteInativo implements ValidadorInterface{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar (DadosAgendamentoConsulta dados){

        var id = dados.idPaciente();
        var paciente = pacienteRepository.getReferenceById(id);
        if (!paciente.getAtivo()){
            throw new ValidacaoException("Paciente inativo, não é possível agendar.");
        }

    }
}
