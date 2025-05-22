package med.voll.api.paciente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.paciente.DTO.PacienteAtualizarDTO;
import med.voll.api.paciente.DTO.PacienteDTO;
import med.voll.api.paciente.DTO.PacienteListagemDTO;
import med.voll.api.paciente.DTO.PacientesDadosDetalhadoDTO;
import med.voll.api.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid PacienteDTO pacienteDTO, UriComponentsBuilder uriComponentsBuilder){
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand().toUri();
        var paciente = pacienteService.cadastrarPaciente(pacienteDTO);
        return ResponseEntity.created(uri).body(new PacientesDadosDetalhadoDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListagemDTO>> listarPacientes(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        var page = pacienteService.listarPacientes(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizacaoPaciente(@RequestBody @Valid PacienteAtualizarDTO pacienteAtualizarDTO){
        var paciente = pacienteService.atualizarPaciente(pacienteAtualizarDTO);
        return ResponseEntity.ok(new PacientesDadosDetalhadoDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable Long id){
        pacienteService.deletarPaciente(id);
        ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente (@PathVariable Long id){
        var paciente = pacienteService.dadoDetalhadoPaciente(id);
        return ResponseEntity.ok(new PacientesDadosDetalhadoDTO(paciente));
    }

}
