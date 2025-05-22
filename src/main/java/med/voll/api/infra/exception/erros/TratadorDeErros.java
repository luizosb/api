package med.voll.api.infra.exception.erros;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.infra.exception.DTO.MensagemErroDTO;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    /**
     * Por enquanto, é uma classe em Java e não há nada em Spring. Isto é, o Spring não irá carregar essa classe automaticamente quando recarregarmos o projeto.
     * Para ele carregar essa classe, é necessário termos à anotação @RestControllerAdvice
     * Dessa forma, o Spring sabe que se em qualquer controller do projeto for lançado uma exceção EntityNotFoundException, é para chamar o metodo tratarErro404().
     * E o que devolvermos, é o que será devolvido como resposta na requisição.
     * Por isso, ao invés de duplicarmos o try-catch no código, podemos usar outro recurso do Spring para isolar esse tipo de tratamento de erros.
     * Note que é simples criar uma classe com métodos que trataram exceções não tratadas no controller.
     * Logo, esse código fica isolado e não precisamos ter try-catch nos controllers.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErros404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErros400(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(MensagemErroDTO::new).toList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarRegraDeNegocio(ValidacaoException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    /**
     * erro.getField(): nos devolve o nome do campo
     * erro.getDefaultMessage(): nos devolve a mensagem para um campo específico.
     */

}
