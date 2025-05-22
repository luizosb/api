package med.voll.api.consulta.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.consulta.DTO.DadosAgendamentoConsulta;
import med.voll.api.consulta.DTO.DadosCancelamentoConsulta;
import med.voll.api.consulta.servico.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultasService agendaDeConsultasService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        var dto = agendaDeConsultasService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity cancelar(DadosCancelamentoConsulta cancelamentoConsulta){
        agendaDeConsultasService.cancelarConsulta(cancelamentoConsulta);
        return ResponseEntity.noContent().build();
    }



}