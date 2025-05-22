package med.voll.api.consulta.servico;

import jakarta.validation.Valid;
import med.voll.api.consulta.DTO.DadosAgendamentoConsulta;
import med.voll.api.consulta.DTO.DadosCancelamentoConsulta;
import med.voll.api.consulta.DTO.DadosDetalhamentoConsulta;
import med.voll.api.consulta.model.Consulta;
import med.voll.api.consulta.repository.ConsultaRepository;
import med.voll.api.consulta.validacoes.ValidadorInterface;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorInterface> validadores;


    public DadosDetalhamentoConsulta agendar(@Valid DadosAgendamentoConsulta dados){
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do medico informado não existe");
        }

        validadores.forEach(v-> v.validar(dados));

        /**
         * Podemos trocar o findById() pelo getReferenceById()
         * também na variável paciente, pois não queremos carregar o objeto para manipula-lo, mas só para atribui-lo a outro objeto.
         */
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null){
            throw new ValidacaoException("Não existe médico disponível");
        }
        var consulta = new Consulta(null, medico, paciente, null, dados.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(@Valid DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

    public void cancelarConsulta(DadosCancelamentoConsulta cancelamentoConsulta) {
        if (!consultaRepository.existsById(cancelamentoConsulta.consulta().getId())){
            throw new ValidacaoException("Consulta inexistente");
        }

        var intervalo = LocalDateTime.now().isBefore(cancelamentoConsulta.consulta().getData().minusHours(24));

        if (!intervalo){
            throw  new ValidacaoException("Consulta não pode ser cancelada por ultrapassar o tempo de 24 horas antes.");
        }

        var consulta = consultaRepository.getReferenceById(cancelamentoConsulta.consulta().getId());
        consulta.cancelar(consulta.getMotivoCancelamento());

    }
}
