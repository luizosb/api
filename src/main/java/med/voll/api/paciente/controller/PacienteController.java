package med.voll.api.paciente.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DTO.PacienteAtualizarDTO;
import med.voll.api.paciente.DTO.PacienteDTO;
import med.voll.api.paciente.DTO.PacienteListagemDTO;
import med.voll.api.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid PacienteDTO pacienteDTO){
        pacienteService.cadastrarPaciente(pacienteDTO);
    }

    @GetMapping
    public Page<PacienteListagemDTO> listarPacientes(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        return pacienteService.listarPacientes(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizacaoPaciente(@RequestBody @Valid PacienteAtualizarDTO pacienteAtualizarDTO){
        pacienteService.atualizarPaciente(pacienteAtualizarDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable Long id){
        pacienteService.deletarPaciente(id);
    }
}
