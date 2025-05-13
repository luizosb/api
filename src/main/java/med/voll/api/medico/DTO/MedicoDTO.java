package med.voll.api.medico.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DTO.EnderecoDTO;
import med.voll.api.medico.model.Especialidade;

/**
 *Os nomes nos DTO tem que ser igual ao que está sendo mandado no JSON. Cammel case
 */
public record MedicoDTO(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        EnderecoDTO endereco
) {
}

/**
 * @NotBlank > utilizado para fazer uma validação para o valor não chegar nulo e nem em braco.
 * @Email > validação para casos em que o dado é um email
 * @NotNull > utilizado nesse caso no lugar de NotBlank pois é uma classe de fora
 * @Pattern >  porque ele é um número de 4 a 6 dígitos. Dentro da segunda anotação, para esclarecer a quantidade de dígitos passaremos a expressão regular, é de 4 a 6 digitos.
 */