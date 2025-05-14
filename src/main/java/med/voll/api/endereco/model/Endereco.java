package med.voll.api.endereco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DTO.EnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.bairro = enderecoDTO.bairro();
        this.cep = enderecoDTO.cep();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.uf = enderecoDTO.uf();
        this.cidade = enderecoDTO.cidade();
    }

    public void atualizarInformacoes(EnderecoDTO endereco) {
        if (endereco.logradouro() != null){
            this.logradouro = endereco.logradouro();
        }
        if (endereco.bairro() != null){
            this.bairro = endereco.bairro();
        }
        if (endereco.cep() != null){
            this.cep = endereco.cep();
        }
        if (endereco.numero() != null){
            this.numero = endereco.numero();
        }
        if (endereco.complemento() != null){
            this.complemento = endereco.complemento();
        }
        if (endereco.uf() != null){
            this.uf = endereco.uf();
        }
        if (endereco.cidade() != null){
            this.cidade = endereco.cidade();
        }
    }
}
