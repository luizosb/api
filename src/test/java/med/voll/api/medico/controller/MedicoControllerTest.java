package med.voll.api.medico.controller;

import med.voll.api.endereco.DTO.EnderecoDTO;
import med.voll.api.endereco.model.Endereco;
import med.voll.api.medico.DTO.MedicoDTO;
import med.voll.api.medico.DTO.MedicoInfoDetalhadaDTO;
import med.voll.api.medico.model.Especialidade;
import med.voll.api.medico.model.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<MedicoDTO> medicoDTOTest;

    @Autowired
    private JacksonTester<MedicoInfoDetalhadaDTO> medicoInfoDetalhadaDTOTest;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deveria retornar erro HTTP 400 por conta de dados inválidos")
    @WithMockUser
    void cadastrarMedicoCenario1() throws Exception {

        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    @WithMockUser
    void cadastrarCenario2() throws Exception {
        Especialidade especialidade = Especialidade.CARDIOLOGIA;

        var dadosCadastro = new MedicoDTO(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                especialidade,
                dadosEndereco());

        when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoDTOTest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new MedicoInfoDetalhadaDTO(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.crm(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new Endereco(dadosCadastro.endereco())
        );
        var jsonEsperado = medicoInfoDetalhadaDTOTest.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "201",
                "opa",
                "Flores",
                "Sampa",
                "SP",
                "00000000"
        );
    }


}