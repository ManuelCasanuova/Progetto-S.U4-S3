package catalogo.bibliotecario.prestiti;

import jakarta.persistence.EntityManager;

import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    //Aggiunta di un prestito
    public void addPrestito(Prestito prestito) {
        em.persist(prestito);
        System.out.println("Prestito del " + prestito.getDataInizioPrestito() + " aggiunto correttamente.");
        System.out.println(" ");
    }

  //Eliminazione del prestito tramite id
    public void removePrestito(Long id) {
        Prestito prestito = em.find(Prestito.class, id);
        if (prestito != null) {
            em.remove(prestito);
            System.out.println("Prestito eliminato correttamente.");
            System.out.println(" ");
        } else {
            System.out.println("Prestito non trovato.");
            System.out.println(" ");
        }
    }

}
