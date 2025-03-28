package catalogo.bibliotecario.utenti;

import jakarta.persistence.EntityManager;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    //Aggiunta di un utente
    public void addUtente(Utente utente) {
        em.persist(utente);
       /* System.out.println("Utente " + utente.getNome() + " " + utente.getCognome()+"," + " tessera numero: " + utente.getNumeroDiTessera() + " aggiunto con successo.");
        System.out.println(" ");*/ // Da scommentare qualora si usassero i singoli main
    }

}
