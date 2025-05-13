package med.voll.api.paciente.controller;

import med.voll.api.paciente.DTO.PacienteDTO;
import med.voll.api.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public void cadastrarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.cadastrarPaciente(pacienteDTO);
    }
}
