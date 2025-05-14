package med.voll.api.medico.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DTO.MedicoDTO;
import med.voll.api.medico.DTO.MedicoListagemDTO;
import med.voll.api.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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


    /**
     *Para buscar paginação com essa rota basta utilizar assim no postman:
     * http://localhost:8080/medicos?size=1&page=1 onde size é a quantidade de dados que irá trazer e page a pagina.
     * São parametros que existem no spring para facilitar o processo.
     *
     * No entanto, utilizamos a anotação PageableDefault para setar a quantidade, paginação e em qual ordem irá trazer os dados,
     * sem necessidade de escrever no Postman. Utilizo o postman apenas caso queira editar minha pesquisa.
     */
    @GetMapping
    public Page<MedicoListagemDTO> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        return medVollService.buscarTodosMedicos(paginacao);
    }
}
