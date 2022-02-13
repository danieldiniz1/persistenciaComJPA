package testes;

import dao.CategoriaDao;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto retornoProduto = produtoDao.buscarPorId(1L);

        System.out.println(retornoProduto.getNome());

        List<Produto> produtos = produtoDao.buscarPorNomeCategoriaNamedQuery("CELULARES");
        produtos.forEach(produto -> System.out.println(produto));

        List<Produto> produtoList = produtoDao.buscarTodos();
        produtoList.forEach(p -> System.out.println(p.getNome() + ", preço: R$ " + p.getPreco()));

        List<Produto> produtoFiltrado = produtoDao.buscarPorNome("Xiaomi Redmi");
        produtoFiltrado.forEach(p -> System.out.println("O produto: " +p.getNome() + ", preço: R$ " + p.getPreco()));

        List<Produto> produtoFiltradoCategoria = produtoDao.buscarPorNome("Xiaomi Redmi");
        produtoFiltradoCategoria.forEach(p -> System.out.println("O produto: " +p.getNome() + ", preço: R$ " + p.getPreco()
                + "da categoria: " + p.getCategoria().getNome()));

        BigDecimal preco = produtoDao.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
        System.out.println("Preço do produto: " + preco);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi",
                "Celular da china", new BigDecimal("800"),celulares);
        Produto celular2 = new Produto("Sansung S10",
                "Celular da Coreia", new BigDecimal("1200"), celulares);

        JPAUtil jpaUtil = new JPAUtil();
        EntityManager em = jpaUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(celular2);

        em.getTransaction().commit();
        em.close();
    }
}
