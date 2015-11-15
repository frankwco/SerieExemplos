package banco;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class DAOGenerico {

    EntityManager em;

    public List lista(Class classe) {
        Query q = null;
        try {
            em = Banco.getInstancia().getEm();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            q = em.createQuery("from " + classe.getSimpleName());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            e.printStackTrace();
        }
        return q.getResultList();
    }

    public List listaCondicao(Class classe, String condicao) {
        em = Banco.getInstancia().getEm();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        Query q = em.createQuery("from " + classe.getSimpleName() + " where " + condicao);
        em.getTransaction().commit();
        return q.getResultList();
    }

    public void inserir(Object marca) {
        try {
            em = Banco.getInstancia().getEm();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(marca);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void exluir(Object objeto) throws Exception {
        EntityTransaction et = null;
        try {
            em = Banco.getInstancia().getEm();
            et = em.getTransaction();
//            if (!em.getTransaction().isActive()) {
//                em.getTransaction().begin();
//            }
            if (!et.isActive()) {
                et.begin();
            }
            Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
            objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
            em.remove(objeto);
//            em.getTransaction().commit();
            et.commit();
        } catch (Exception e) {
            System.out.println("caiu no rollback antes");
//            em.getTransaction().rollback();

            et.rollback();
            System.out.println("caiu no rollback depois");
            e.printStackTrace();
        }

    }

    public void salvar(Object objeto) {
        em = Banco.getInstancia().getEm();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.merge(objeto);
        em.getTransaction().commit();
    }

    public Object recupera(Class classe, Long id) {
        em = Banco.getInstancia().getEm();
        Object retornando = null;
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }

        retornando = em.find(classe, id);
        em.getTransaction().commit();
        return retornando;
    }
}
