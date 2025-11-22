package cafeteria.dto;

import cafeteria.model.entities.Produto;
import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        String categoria
) {

    public ProdutoResponse(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria().toString()
        );
    }
}