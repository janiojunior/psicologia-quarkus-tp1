package br.unitins.topicos1.dto;

import java.util.List;

public record PedidoDTO (
    Long idPaciente,
    Integer tipoPagamento,
    String nomeCartao,
    String numeroCartao,
    List<ItemPedidoDTO> itens) 
{ }
