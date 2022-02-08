package dao;

import modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }

    public void atualizar(Pedido pedido){
        this.em.merge(pedido);
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql,BigDecimal.class).getSingleResult();
    }

}
