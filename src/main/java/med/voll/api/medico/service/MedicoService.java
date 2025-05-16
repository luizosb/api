package med.voll.api.medico.service;

import jakarta.validation.Valid;
import med.voll.api.medico.DTO.MedicoAtualizarDTO;
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

    public Medico cadastrarMedico(MedicoDTO medicoDTO) {
        return medicoRepository.save(new Medico(medicoDTO));
    }

    public Page<MedicoListagemDTO> buscarTodosMedicos(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new);
    }

    /**
     *Como estou acessando o dado apenas pelo ID e não pelo corpo, eu uso getReferenceByID e não findByID.
     * FindbyID -> eager, traz todos os dados.
     * GetReferenceById -> lazy, traz apenas os dados sob demanda.
     */
    public Medico atualizarDadoMedico(@Valid MedicoAtualizarDTO medicoAtualizarDTO) {
        var medico = medicoRepository.getReferenceById(medicoAtualizarDTO.id());
        medico.atualizarDados(medicoAtualizarDTO);
        return medico;
    }

    public void deletarMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }

    public Medico dadoDetalhadoMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return medico;
    }


}
