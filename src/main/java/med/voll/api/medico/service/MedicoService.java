package med.voll.api.medico.service;

import med.voll.api.medico.DTO.MedicoDTO;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public void cadastrarMedico(MedicoDTO medicoDTO) {
        medicoRepository.save(new Medico(medicoDTO));
    }
}
