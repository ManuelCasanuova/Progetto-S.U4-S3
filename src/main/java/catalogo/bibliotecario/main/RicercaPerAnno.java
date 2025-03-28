package catalogo.bibliotecario.main;

import catalogo.bibliotecario.cataloghi.Catalogo;
import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class RicercaPerAnno {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();

        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();

        System.out.println("Ricerca per aunno di pubblicazione: ");
        System.out.println(" ");

        List<Catalogo> elementoCatalogoPerAnno = catalogoDAO.findByAnnoPubblicazione("2020");
        elementoCatalogoPerAnno.forEach(System.out::println);


        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
