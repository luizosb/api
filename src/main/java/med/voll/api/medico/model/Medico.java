package med.voll.api.medico.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.model.Endereco;
import med.voll.api.medico.DTO.MedicoAtualizarDTO;
import med.voll.api.medico.DTO.MedicoDTO;

/**
 * Embeddable Attribute da JPA para que Endereco fique em uma classe separada, mas faça parte da mesma tabela de Medicos junto ao banco de dados.
 */
@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoDTO medicoDTO) {
        this.nome = medicoDTO.nome();
        this.email = medicoDTO.email();
        this.crm = medicoDTO.crm();
        this.telefone = medicoDTO.telefone();
        this.endereco = new Endereco(medicoDTO.endereco());
        this.especialidade = medicoDTO.especialidade();
        this.ativo = true;
    }

    public void atualizarDados(@Valid MedicoAtualizarDTO medicoAtualizarDTO) {
        if (medicoAtualizarDTO.nome() != null){
            this.nome = medicoAtualizarDTO.nome();
        }
        if (medicoAtualizarDTO.telefone() != null){
            this.telefone = medicoAtualizarDTO.telefone();
        }
        if (medicoAtualizarDTO.endereco() != null){
            this.endereco.atualizarInformacoes(medicoAtualizarDTO.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
