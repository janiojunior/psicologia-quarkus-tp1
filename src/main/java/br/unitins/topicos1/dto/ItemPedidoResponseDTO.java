package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ItemPedido;

public record ItemPedidoResponseDTO (
    Long id,
    String nome,
    Double desconto,
    Integer quantidade
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getId(), 
            item.getConsulta().getNome(), 
            item.getDesconto(),
            item.getQuantidade());
    }
}
