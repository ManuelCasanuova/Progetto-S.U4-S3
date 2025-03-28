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

}
