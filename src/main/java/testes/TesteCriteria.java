package testes;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelo.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteCriteria {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = entityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        produtoDao.buscarPorParametroComCriteria("jose", null, LocalDate.now());
    }

    private static EntityManager entityManager(){
        return JPAUtil.getEntityManager();
    }

    private static void popularBancoDeDados() {
        Cliente jose = new Cliente("jose", "12345678998");
        Cliente pedro = new Cliente("pedro", "32165498798");
        Categoria celulares = new Categoria("CELULARES");
        Categoria videoGames = new Categoria("VIDEOGAMEs");
        Categoria computadores = new Categoria("VIDEOGAMEs");

        Produto celular = new Produto("Xiaomi Redmi", "Celular da china", new BigDecimal("800"), celulares);
        Produto celular2 = new Produto("Sansung S10", "Celular da Coreia", new BigDecimal("1200"), celulares);
        Produto videoGame = new Produto("PS5", "otimo videogame", new BigDecimal("2000.98"));
        Produto computador = new Produto("Macbook", "MacbookPro", new BigDecimal("10050.78"));

        Pedido pedido = new Pedido(jose);
        ItemPedido itemPedido = new ItemPedido(2,pedido,celular);
        pedido.adicionarItem(itemPedido);

        JPAUtil jpaUtil = new JPAUtil();
        EntityManager em = jpaUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);


        em.getTransaction().begin();

        clienteDao.cadastrar(jose);
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videoGames);
        categoriaDao.cadastrar(computadores);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(videoGame);
        produtoDao.cadastrar(computador);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
        em.close();


    }
}


