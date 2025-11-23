package cafeteria.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false, precision = 6, scale = 2 )
    private BigDecimal valor;

    @Column(name = "datahora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    @Column(name = "datahora_finalizacao")
    private LocalDateTime dataHoraFinalizacao;

    @Column(name = "status", nullable = false)
    private String status = "ABERTO";
    
    //ForeignKey para Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //ForeignKey para Atendente
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Atendente atendente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    
    @PrePersist
    public void aoCriar() {
        this.dataHora = LocalDateTime.now();
    }

    public Pedido (){}

    public Pedido(Cliente cliente, Atendente atendente) {
        //this.id =
        //this.valor = recalcularValor()
        //this.dataHora = método "aoCriar"
        this.cliente = cliente;
        this.atendente = atendente;
        this.status = "ABERTO";
    }


    public void addItem(ItemPedido item) {
        if (!"ABERTO".equals(this.status))
            throw new IllegalStateException("Só é possível adicionar itens em pedidos ABERTOS.");

        item.setPedido(this);
        item.calcularSubTotal();
        this.itens.add(item);
        recalcularValor();
    }

    public void finalizar() {
        if ("FINALIZADO".equals(this.status))
            throw new IllegalStateException("O pedido já está finalizado.");

        this.status = "FINALIZADO";
        this.dataHoraFinalizacao = LocalDateTime.now();
    }

    private void recalcularValor() {
        this.valor = itens.stream()
                .map(ItemPedido::getSubValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
        
    /*
    Getters and
     Setters
    */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.dataHora = datahora;
    }

    public LocalDateTime getDataHoraFinalizacao() {
        return dataHoraFinalizacao;
    }

    public void setDataHoraFinalizacao(LocalDateTime dataHoraFinalizacao) {
        this.dataHoraFinalizacao = dataHoraFinalizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public List<ItemPedido> getItens() { return itens; }

}
