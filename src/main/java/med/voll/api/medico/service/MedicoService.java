package med.voll.api.medico.service;

import med.voll.api.medico.DTO.MedicoDTO;
import med.voll.api.medico.DTO.MedicoListagemDTO;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public void cadastrarMedico(MedicoDTO medicoDTO) {
        medicoRepository.save(new Medico(medicoDTO));

    }

    public Page<MedicoListagemDTO> buscarTodosMedicos(Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(MedicoListagemDTO::new);
    }
}
