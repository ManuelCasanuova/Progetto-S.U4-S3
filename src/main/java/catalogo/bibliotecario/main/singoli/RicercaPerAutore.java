package catalogo.bibliotecario.main.singoli;

import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RicercaPerAutore {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();

        CatalogoDAO catalogoDAO= new CatalogoDAO(em);

        em.getTransaction().begin();

        System.out.println(" ");
        System.out.println("Ricerca per autore: ");
        System.out.println(" ");

        catalogoDAO.findByAutore("Alberto Moravia");
        //catalogoDAO.findByAutore("Ludwig van Beethoven");



        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
