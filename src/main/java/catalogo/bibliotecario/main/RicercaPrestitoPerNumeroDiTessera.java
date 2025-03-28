package catalogo.bibliotecario.main;

import catalogo.bibliotecario.cataloghi.Catalogo;
import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import catalogo.bibliotecario.prestiti.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class RicercaPrestitoPerNumeroDiTessera {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();

        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();

        System.out.println("Ricerca per titolo: ");
        System.out.println(" ");

        List<Prestito> prestitiUtente = catalogoDAO.findByNumeroTesseraUtente("1234");
        prestitiUtente.forEach(System.out::println);


        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
