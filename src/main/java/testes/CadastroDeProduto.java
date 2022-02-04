package testes;

import dao.ProdutoDao;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto("Xiaomi Redmi","Celular da china", new BigDecimal("800"));

        JPAUtil jpaUtil = new JPAUtil();
        EntityManager em = jpaUtil.getEntityManager();

        ProdutoDao dao = new ProdutoDao(em);
        em.getTransaction().begin();
        dao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();

    }
}
