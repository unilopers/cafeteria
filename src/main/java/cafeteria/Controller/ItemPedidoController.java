package cafeteria.Controller;

import cafeteria.model.entities.ItemPedido;
import cafeteria.model.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService service;

    //recebe os dados do postman
    public record ItemPedidoDto(Integer pedidoId, Long produtoId, Integer quantidade) {}

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarItem(@RequestBody ItemPedidoDto dados) {
        try { ItemPedido novoItem = service.adicionarItem(
                    dados.pedidoId,
                    dados.produtoId,
                    dados.quantidade
            );
            return ResponseEntity.status(201).body(novoItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: "+e.getMessage());
        }
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedido>> listarPorPedido(@PathVariable Integer pedidoId) {
        return ResponseEntity.ok(service.listarItensDoPedido(pedidoId));
    }
}
