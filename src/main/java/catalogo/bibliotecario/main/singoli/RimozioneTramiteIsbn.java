package catalogo.bibliotecario.main.singoli;

import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RimozioneTramiteIsbn {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();

        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();


        System.out.println("Rimozione di un elemento dal catalogo dato il codice ISBN: ");
        System.out.println(" ");


        catalogoDAO.removeByISBN(7L);


        em.getTransaction().commit();
        em.close();
        emf.close();
    }



}
