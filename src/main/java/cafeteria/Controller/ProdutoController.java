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
    private ProdutoController repository;

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
    
    
    //POST
    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@RequestBody Produto produto) {
        Produto salvo = service.criar(produto);

        return ResponseEntity
                .created(URI.create("/produtos/" + salvo.getId()))
                .body(new ProdutoResponse(salvo));
    }

    //atualizar
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado
    ) {
        Produto atualizado = service.atualizar(id, produtoAtualizado);
        return ResponseEntity.ok(new ProdutoResponse(atualizado));
    }

    //remover
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    
    
}
