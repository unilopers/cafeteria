package cafeteria.Controller;

import cafeteria.dto.ProdutoResponse;
import cafeteria.model.entities.Produto;
import cafeteria.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos") // O endereço será: http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // GET - listar todos
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        List<ProdutoResponse> resposta = service.listarTodos()
                .stream()
                .map(ProdutoResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

     //Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return ResponseEntity.ok(new ProdutoResponse(produto));
    }
    
    
    // Rota POST: Criar um produto novo
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto salvo = repository.save(produto);
        return ResponseEntity.status(201).body(salvo);
    }
}
