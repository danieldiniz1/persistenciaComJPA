package testes;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelo.*;
import util.JPAUtil;
import vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(1L);
        ClienteDao clienteDao = new ClienteDao(em);
        Cliente jose = clienteDao.buscarPorId(1);
        Cliente pedro = clienteDao.buscarPorId(2);

        Pedido pedido = new Pedido(jose);
        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        pedido.adicionarItem(new ItemPedido(10,pedido,produtoDao.buscarPorId(3L)));

        Pedido pedido2 = new Pedido(pedro);
        pedido2.adicionarItem(new ItemPedido(2,pedido2,produtoDao.buscarPorId(3L)));

        em.getTransaction().begin();


        clienteDao.cadastrar(jose);

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        BigDecimal valorTotalDoPedido = pedidoDao.valorTotalVendido();
        List<RelatorioDeVendasVo> relatorioDeVendasVos = pedidoDao.relatorioDeVendas();

        em.getTransaction().commit();
        em.close();

        System.out.println("Valor total do pedido: R$ " + valorTotalDoPedido);

        relatorioDeVendasVos.forEach(atributo -> System.out.println(atributo));

    }

    private static void popularBancoDeDados() {
        Cliente jose = new Cliente("jose","12345678998");
        Cliente pedro = new Cliente("pedro","32165498798");
        Categoria celulares = new Categoria("CELULARES");
        Categoria videoGames = new Categoria("VIDEOGAMEs");
        Categoria computadores = new Categoria("VIDEOGAMEs");

        Produto celular = new Produto("Xiaomi Redmi",
                "Celular da china", new BigDecimal("800"),celulares);
        Produto celular2 = new Produto("Sansung S10",
                "Celular da Coreia", new BigDecimal("1200"), celulares);
        Produto videoGame = new Produto("PS5","otimo videogame",new BigDecimal("2000.98"));
        Produto computador = new Produto("Macbook","MacbookPro",new BigDecimal("10050.78"));

        JPAUtil jpaUtil = new JPAUtil();
        EntityManager em = jpaUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        clienteDao.cadastrar(jose);
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videoGames);
        categoriaDao.cadastrar(computadores);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(videoGame);
        produtoDao.cadastrar(computador);

        em.getTransaction().commit();
        em.close();
    }
}
