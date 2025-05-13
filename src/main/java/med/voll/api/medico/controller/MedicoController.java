package med.voll.api.medico.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DTO.MedicoDTO;

import med.voll.api.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medVollService;

    /**
     * Para pegar do corpo da requisição, para informarmos isso para o Spring, incluiremos a anotação @RequestBody neste parâmetro.
     * Vamos inserir a anotação @Transactional, pois
     * como esse é um metodo de escrita, que consiste em um insert no banco de dados, precisamos ter uma transação ativa com ele.
     */
    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid MedicoDTO medicoDTO){
        medVollService.cadastrarMedico(medicoDTO);
    }

}
