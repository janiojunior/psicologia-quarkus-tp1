package br.unitins.topicos1.dto;

public record ItemPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idConsulta
) {

}
