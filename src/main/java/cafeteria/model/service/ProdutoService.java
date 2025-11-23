package cafeteria.model.service;

import cafeteria.model.entities.Produto;
import cafeteria.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto criar(Produto produto) {
        validarProduto(produto);
        return repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: id=" + id));
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto existente = buscarPorId(id);

        validarProduto(produtoAtualizado);

        existente.setNome(produtoAtualizado.getNome());
        existente.setPreco(produtoAtualizado.getPreco());
        existente.setCategoria(produtoAtualizado.getCategoria());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        Produto existente = buscarPorId(id);
        repository.delete(existente);
    }

    //Validações básicas
    private void validarProduto(Produto p) {
        if (p.getNome() == null || p.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        if (p.getPreco() == null || p.getPreco().doubleValue() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        if (p.getCategoria() == null) {
            throw new IllegalArgumentException("A categoria é obrigatória.");
        }
    }
}
