package dao;

import modelo.Pedido;

import javax.persistence.EntityManager;

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

}
