package med.voll.api.consulta;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

        agendaDeConsultasService.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

    public ResponseEntity cancelar(DadosCancelamentoConsulta cancelamentoConsulta){
        agendaDeConsultasService.cancelarConsulta(cancelamentoConsulta);
        return ResponseEntity.noContent().build();
    }



}