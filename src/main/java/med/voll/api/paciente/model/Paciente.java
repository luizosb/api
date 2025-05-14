package med.voll.api.paciente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DTO.EnderecoDTO;
import med.voll.api.endereco.model.Endereco;
import med.voll.api.paciente.DTO.PacienteAtualizarDTO;
import med.voll.api.paciente.DTO.PacienteDTO;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.nome();
        this.cpf = pacienteDTO.cpf();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.telefone();
        this.endereco = new Endereco(pacienteDTO.endereco());
        this.ativo = true;
    }

    public void atualizarDado(PacienteAtualizarDTO pacienteAtualizarDTO) {
        if (pacienteAtualizarDTO.nome() != null) {
            this.nome = pacienteAtualizarDTO.nome();
        }
        if (pacienteAtualizarDTO.telefone() != null) {
            this.telefone = pacienteAtualizarDTO.telefone();
        }
        if (pacienteAtualizarDTO.nome() != null) {
            this.endereco = new Endereco(pacienteAtualizarDTO.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
