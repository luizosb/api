package med.voll.api.consulta;

import jakarta.validation.Valid;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public void agendar(@Valid DadosAgendamentoConsulta dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do medico informado não existe");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(@Valid DadosAgendamentoConsulta dados) {
        return null;
    }
}
