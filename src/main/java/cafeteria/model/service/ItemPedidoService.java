package cafeteria.model.service;

import cafeteria.model.entities.ItemPedido;
import cafeteria.model.entities.Pedido;
import cafeteria.model.entities.Produto;
import cafeteria.model.repository.ItemPedidoRepository;
import cafeteria.model.repository.PedidoRepository;
import cafeteria.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //adição do item
    public ItemPedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade) throws Exception {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new Exception("Pedido não encontrado com ID: " + pedidoId));

        if ("FINALIZADO".equals(pedido.getStatus())) {
            throw new IllegalStateException("Não é possível adicionar itens em um pedido finalizado.");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new Exception("Produto não encontrado com ID: " + produtoId));

        // Cria o item
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);

        // Calcula subtotal
        item.calcularSubTotal();

        // Adiciona o item usando REGRAS DE NEGÓCIO DO PEDIDO
        pedido.addItem(item);

        // Salva o item e o pedido com valor recalculado
        itemRepository.save(item);
        pedidoRepository.save(pedido);

        return item;
    }

    //Lista itens de um pedido
    public List<ItemPedido> listarItensDoPedido(Integer pedidoId) {
        return itemRepository.findByPedidoId(pedidoId);
    }
}
