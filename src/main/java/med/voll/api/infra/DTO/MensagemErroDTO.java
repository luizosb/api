package med.voll.api.infra.DTO;

import org.springframework.validation.FieldError;

public record MensagemErroDTO(String campo, String mensagem) {
    public MensagemErroDTO(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
