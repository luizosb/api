package med.voll.api.usuario.controller;

import jakarta.validation.Valid;
import med.voll.api.infra.security.TokenJWTDTO;
import med.voll.api.infra.security.TokenService;
import med.voll.api.usuario.DTO.UsuarioDTO;
import med.voll.api.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que esta classe é um controller REST que retorna JSON
@RequestMapping("/login") // Define que todas as requisições para esse controller começam com /login
public class UsuarioController {

    @Autowired // Injeta automaticamente o AuthenticationManager configurado no projeto
    private AuthenticationManager manager;

    @Autowired // Injeta o serviço responsável por gerar o token JWT
    private TokenService tokenService;

    @PostMapping // Mapeia requisições POST para o endpoint /login
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        // Cria o token de autenticação do Spring com login e senha fornecidos
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.login(), usuarioDTO.senha());

        // Executa o processo de autenticação. Isso verifica:
        // - Se o login existe
        // - Se a senha está correta (usando o PasswordEncoder)
        // - Se o usuário está ativo, não bloqueado, etc.
        var authentication = manager.authenticate(authenticationToken);

        // Recupera o usuário autenticado (getPrincipal()) e gera o token JWT
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Retorna o token JWT encapsulado em um DTO no corpo da resposta (status 200 OK)
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}
