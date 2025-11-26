package cafeteria.Controller;

import cafeteria.dto.ProdutoResponse;
import cafeteria.model.entities.Produto;
import cafeteria.model.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // LISTAR TODOS
    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos() {
        try {
            List<ProdutoResponse> resposta = service.listarTodos()
                    .stream()
                    .map(ProdutoResponse::new)
                    .toList();

            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar produtos: " + e.getMessage());
        }
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Produto produto = service.buscarPorId(id);
            return ResponseEntity.ok(new ProdutoResponse(produto));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar produto: " + e.getMessage());
        }
    }

    // CRIAR
    @PostMapping("/novo")
    public ResponseEntity<?> criar(@RequestBody Produto produto) {
        try {
            Produto salvo = service.criar(produto);

            return ResponseEntity
                    .created(URI.create("/produto/" + salvo.getId()))
                    .body(new ProdutoResponse(salvo));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar produto: " + e.getMessage());
        }
    }

    // ATUALIZAR
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado
    ) {
        try {
            Produto atualizado = service.atualizar(id, produtoAtualizado);
            return ResponseEntity.ok(new ProdutoResponse(atualizado));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // DELETAR
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
