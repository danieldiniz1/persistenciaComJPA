package modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data = LocalDate.now();
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }
}
