package br.unitins.topicos1.dto;

import java.util.List;

public record PedidoDTO (
    Long idPaciente,
    List<ItemPedidoDTO> itens) 
{ }
