package cafeteria.dto;

import java.math.BigDecimal;

public record PedidoDTO(
        Long cliente_id,
        Long atendente_id
) {}
