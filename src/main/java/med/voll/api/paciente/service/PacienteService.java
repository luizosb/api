package med.voll.api.paciente.service;

import jakarta.validation.Valid;
import med.voll.api.paciente.DTO.PacienteAtualizarDTO;
import med.voll.api.paciente.DTO.PacienteDTO;
import med.voll.api.paciente.DTO.PacienteListagemDTO;
import med.voll.api.paciente.model.Paciente;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public void cadastrarPaciente(PacienteDTO pacienteDTO){
        pacienteRepository.save(new Paciente(pacienteDTO));
    }

    public Page<PacienteListagemDTO> listarPacientes(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(PacienteListagemDTO::new);
    }

    public void atualizarPaciente(@Valid PacienteAtualizarDTO pacienteAtualizarDTO) {
        var paciente = pacienteRepository.getReferenceById(pacienteAtualizarDTO.id());
        paciente.atualizarDado(pacienteAtualizarDTO);
    }

    public void deletarPaciente(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }
}
