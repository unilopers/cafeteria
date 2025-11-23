package cafeteria.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itempedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itempedido_id")
    private Integer id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "subvalor", nullable = false, precision = 10, scale = 2)
    private BigDecimal subValor;


    //Relacionamento com Pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    //Relacionamento com Produto
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;


    public ItemPedido() {
    }

    @PrePersist
    @PreUpdate
    public void calcularSubTotal() {
        if (produto == null)
            throw new IllegalStateException("ItemPedido precisa ter um produto.");

        this.subValor = produto.getPreco()
                .multiply(new BigDecimal(this.quantidade));
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public BigDecimal getSubValor() { return subValor; }
    public void setSubValor(BigDecimal subValor) { this.subValor = subValor; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
}
