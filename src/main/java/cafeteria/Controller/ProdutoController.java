package cafeteria.Controller;

import cafeteria.dto.ProdutoResponse;
import cafeteria.model.entities.Produto;
import cafeteria.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos") // O endereço será: http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Rota GET: Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        List<Produto> produtos = repository.findAll();

        // Transforma cada Produto em ProdutoResponse (DTO)
        List<ProdutoResponse> resposta = produtos.stream()
                .map(ProdutoResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    // Rota POST: Criar um produto novo
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto salvo = repository.save(produto);
        return ResponseEntity.status(201).body(salvo);
    }
}