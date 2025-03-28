package catalogo.bibliotecario.main.singoli;

import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RicercaPrestitoPerNumeroDiTessera {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();

        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();
        System.out.println(" ");
        System.out.println("Ricerca per numero della tessera:");
        System.out.println(" ");

        catalogoDAO.findByNumeroTesseraUtente(4L);


        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
