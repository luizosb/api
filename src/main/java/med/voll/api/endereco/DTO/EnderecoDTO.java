package med.voll.api.endereco.DTO;

public record EnderecoDTO(String logradouro,
                          String número,
                          String complemento,
                          String bairro,
                          String cidade,
                          String UF,
                          String CEP) {
}
