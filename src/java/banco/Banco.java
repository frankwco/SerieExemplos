/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 *
 * @author Frank
 */
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Banco {

    private static Banco instancia;
    private EntityManager em;

    private Banco() {
        em = Persistence.createEntityManagerFactory("SerieExemplosPU").createEntityManager();
    }

    public synchronized static Banco getInstancia() {
        if (instancia == null) {          
            instancia = new Banco();
        }
        return instancia;
    }

    public EntityManager getEm() {
        return em;
    }

}
