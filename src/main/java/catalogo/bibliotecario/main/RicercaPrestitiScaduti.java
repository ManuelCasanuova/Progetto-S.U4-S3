package catalogo.bibliotecario.main;


import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import catalogo.bibliotecario.prestiti.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.List;

public class RicercaPrestitiScaduti {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();
        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();

        List<Prestito> prestitiScaduti= catalogoDAO.findPrestitiScadutiNonRestituiti();
        prestitiScaduti.forEach(System.out::println);

        em.getTransaction().commit();
        em.close();
        emf.close();



    }
}
