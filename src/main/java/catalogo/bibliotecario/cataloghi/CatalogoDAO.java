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
      /*  System.out.println("Elemento " + catalogo.getTitolo() + " aggiunto con successo.");
        System.out.println(" ");*/ // Da scommentare qualora si usassero i singoli main
    }


    //Ricerca per ISBN
    public Catalogo findByISBN(Long isbn) {
        Catalogo catalogo = em.find(Catalogo.class, isbn);
        if (catalogo == null) {
            System.out.println("Elemento non trovato");
            System.out.println(" ");
        } else{
            System.out.println("Elemento con isbn: " + catalogo.getIsbn() + " trovato" );
            System.out.println("Titolo: " + catalogo.getTitolo());
            System.out.println(" ");
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
        if(cataloghi.isEmpty() == true) {
            System.out.println("Nessun elemento trovato");
            System.out.println(" ");
        }
        for(Catalogo catalogo : cataloghi){
            System.out.println("Titolo: "+ catalogo.getTitolo() + "  " + "ISBN: " + catalogo.getIsbn() + " - " + "Anno di pubblicazione: " + catalogo.getAnnoPubblicazione());
            System.out.println(" ");

        }

        return cataloghi;
    }

    //Ricerca per autore
    public List<Catalogo> findByAutore(String autore) {
        List<Catalogo> cataloghi = em.createQuery("SELECT catalogo FROM Catalogo catalogo WHERE catalogo.autore = : autore", Catalogo.class)
                .setParameter("autore", autore)
                .getResultList();
        System.out.println("Elementi trovati per autore: " + autore);
        if(cataloghi.isEmpty() == true) {
            System.out.println("Nessun elemento trovato per l'autore: " + autore);
            System.out.println(" ");
        }
        for(Catalogo catalogo : cataloghi){
            System.out.println("Titolo: "+ catalogo.getTitolo() + "  " + "ISBN: " + catalogo.getIsbn());
            System.out.println(" ");

        }
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
    public List<Prestito> findByNumeroTesseraUtente(Long numeroTesseraUtente) {
        try {
            List<Prestito> prestiti = em.createQuery("SELECT prestito FROM Prestito prestito WHERE prestito.utente.numeroDiTessera = :numeroTesseraUtente", Prestito.class)
                    .setParameter("numeroTesseraUtente", numeroTesseraUtente)
                    .getResultList();
            if(prestiti.isEmpty() == true) {
                System.out.println("Nessun elemento in prestito per la tessera: " + numeroTesseraUtente);
            } else {
                System.out.println("Elementi in prestito per la tessera: " + numeroTesseraUtente);
                for(Prestito prestito : prestiti) {
                    System.out.println("Titolo: "+ prestito.getElementoPrestato().getTitolo() + " - " + "ISBN: " + prestito.getElementoPrestato().getIsbn() );
                    System.out.println("Utente: "+ prestito.getUtente().getNome() + " " + prestito.getUtente().getCognome());
                    System.out.println("Data del prestito: " + prestito.getDataInizioPrestito());
                    System.out.println(" ");
                }
            }
            return prestiti;
        } catch (Exception e) {
            System.out.println("Errore durante il recupero dei prestiti: " + e.getMessage());
            throw new RuntimeException("Errore durante il recupero dei prestiti", e);
        }

    }

    //Ricerca di tutti i prestiti scaduti e non ancora restituiti
    public void findPrestitiScadutiNonRestituiti() {
        try {
            List<Prestito> prestiti = em.createQuery("SELECT prestito FROM Prestito prestito WHERE prestito.dataRestituzionePrevista < CURRENT_DATE AND prestito.dataRestituzioneEffettiva IS NULL", Prestito.class)
                    .getResultList();
            if(prestiti.isEmpty() == true) {
                System.out.println("Nessun prestito scaduto e non ancora restituito");
            } else {
                System.out.println("Prestiti scaduti e non ancora restituiti: ");
                for(Prestito prestito : prestiti) {
                    System.out.println("ISBN" + prestito.getElementoPrestato().getIsbn() + " - " + "Titolo: " + prestito.getElementoPrestato().getTitolo() + " - " + "Data di restituzione prevista: " + prestito.getDataRestituzionePrevista());
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante il recupero dei prestiti: " + e.getMessage());
            throw new RuntimeException("Errore durante il recupero dei prestiti", e);
        }

    }

}
