package catalogo.bibliotecario.cataloghi;

import catalogo.bibliotecario.prestiti.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CatalogoDAO {
    private EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    //Aggiunta di un elemento del catalogo
    public void addElemento(Catalogo catalogo) {
        em.persist(catalogo);
        System.out.println("Elemento " + catalogo.getTitolo() + " aggiunto con successo.");
        System.out.println(" ");
    }


    //Ricerca per ISBN
    public Catalogo  findByISBN(Long isbn) {
        Catalogo catalogo = em.find(Catalogo.class, isbn);
        if (catalogo == null) {
            System.out.println("Elemento non trovato");
        } else{
            System.out.println("Elemento con isbn: " + catalogo.getIsbn() + " trovato" );
        }
        return catalogo;

    }

    //Rimozione di un elemento del catalogo dato un codice ISBN

   public void removeByISBN(Long isbn) {
       try {
           Catalogo catalogo = findByISBN(isbn);
           if (catalogo != null) {
               em.remove(catalogo);
               System.out.println("Elemento con isbn: " + catalogo.getIsbn() + " rimosso con successo.");
           } else {
               System.out.println("Elemento con isbn: " + isbn + " non trovato" );
           }

       } catch (Exception e) {
           System.out.println("Errore durante la rimozione: " + e.getMessage());
           throw new RuntimeException("Errore durante la rimozione dell'elemento", e);
       }
   }

    //Ricerca per anno di pubblicazione
    public List<Catalogo> findByAnnoPubblicazione(String annoPubblicazione) {
           List<Catalogo> cataloghi = em.createQuery("SELECT catalogo FROM Catalogo catalogo WHERE catalogo.annoPubblicazione = : annoPubblicazione", Catalogo.class)
                .setParameter("annoPubblicazione", annoPubblicazione)
                .getResultList();
        System.out.println("Elementi trovati per anno di pubblicazione: " + annoPubblicazione);
        return cataloghi;
    }

    //Ricerca per autore
    public List<Catalogo> findByAutore(String autore) {
        List<Catalogo> cataloghi = em.createQuery("SELECT catalogo FROM Catalogo catalogo WHERE catalogo.autore = : autore", Catalogo.class)
                .setParameter("autore", autore)
                .getResultList();
        System.out.println("Elementi trovati per autore: " + autore);
        return cataloghi;
    }

    //Ricerca per titolo o parte di esso
    public List<Catalogo> findByTitolo(String titolo) {
        List<Catalogo> cataloghi = em.createQuery("SELECT catalogo FROM Catalogo catalogo WHERE catalogo.titolo LIKE : titolo", Catalogo.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
        System.out.println("Elementi trovati per titolo: " + titolo);
        return cataloghi;
    }

    //Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
    public List<Prestito> findByNumeroTesseraUtente(String numeroTesseraUtente) {
        return em.createQuery("SELECT prestito FROM Prestito prestito WHERE prestito.utente.numeroTessera = :numeroTesseraUtente", Prestito.class)
                .setParameter("numeroTesseraUtente", numeroTesseraUtente)
                .getResultList();
    }

    //Ricerca di tutti i prestiti scaduti e non ancora restituiti
    public List<Prestito> findPrestitiScadutiNonRestituiti() {
        return em.createQuery("SELECT prestito FROM Prestito prestito WHERE prestito.dataRestituzionePrevista < CURRENT_DATE AND prestito.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .getResultList();
    }

}
