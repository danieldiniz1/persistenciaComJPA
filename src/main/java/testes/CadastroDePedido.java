package testes;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelo.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(1L);
        ClienteDao clienteDao = new ClienteDao(em);
        Cliente jose = clienteDao.buscarPorId(1);

        Pedido pedido = new Pedido(jose);

        pedido.adicionarItem(new ItemPedido(10,pedido,produto));

        em.getTransaction().begin();


        clienteDao.cadastrar(jose);

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
        em.close();

        System.out.println();

    }

    private static void popularBancoDeDados() {
        Cliente jose = new Cliente("jose","12345678998");
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi",
                "Celular da china", new BigDecimal("800"),celulares);
        Produto celular2 = new Produto("Sansung S10",
                "Celular da Coreia", new BigDecimal("1200"), celulares);

        JPAUtil jpaUtil = new JPAUtil();
        EntityManager em = jpaUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        clienteDao.cadastrar(jose);
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);

        em.getTransaction().commit();
        em.close();
    }
}
