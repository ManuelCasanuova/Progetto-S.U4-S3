package catalogo.bibliotecario.main.singoli;


import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RicercaPrestitiScaduti {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();
        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();

       catalogoDAO.findPrestitiScadutiNonRestituiti();

        em.getTransaction().commit();
        em.close();
        emf.close();



    }
}
