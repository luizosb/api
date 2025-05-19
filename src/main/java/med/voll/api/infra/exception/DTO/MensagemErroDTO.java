package med.voll.api.infra.exception.DTO;

import org.springframework.validation.FieldError;

public record MensagemErroDTO(String campo, String mensagem) {
    public MensagemErroDTO(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
