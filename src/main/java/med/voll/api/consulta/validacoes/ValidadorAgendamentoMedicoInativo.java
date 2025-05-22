package med.voll.api.consulta.validacoes;

import med.voll.api.consulta.DTO.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoMedicoInativo implements ValidadorInterface{

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar (DadosAgendamentoConsulta dados){

        if(dados.idMedico() == null){
            return;
        }


        var id = dados.idMedico();
        var medico = medicoRepository.getReferenceById(id);
        if (!medico.getAtivo()){
            throw new ValidacaoException("Médico inativo, não é possível agendar.");
        }

    }
}
