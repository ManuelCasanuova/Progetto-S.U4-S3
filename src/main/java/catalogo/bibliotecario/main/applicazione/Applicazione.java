package catalogo.bibliotecario.main.applicazione;

import catalogo.bibliotecario.cataloghi.Catalogo;
import catalogo.bibliotecario.cataloghi.CatalogoDAO;
import catalogo.bibliotecario.cataloghi.libri.Libro;
import catalogo.bibliotecario.cataloghi.riviste.Periodicita;
import catalogo.bibliotecario.cataloghi.riviste.Rivista;
import catalogo.bibliotecario.prestiti.Prestito;
import catalogo.bibliotecario.prestiti.PrestitoDAO;
import catalogo.bibliotecario.utenti.Utente;
import catalogo.bibliotecario.utenti.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class Applicazione {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("epicode");
        EntityManager em= emf.createEntityManager();
        Scanner scanner= new Scanner(System.in);

        CatalogoDAO catalogoDAO= new CatalogoDAO(em);
        PrestitoDAO prestitoDAO= new PrestitoDAO(em);
        UtenteDAO utenteDAO= new UtenteDAO(em);

        Catalogo rivista1 = new Rivista(null, "Vogue", 2025, 200, Periodicita.MENSILE);
        Catalogo rivista2= new Rivista(null, "New York Times", 2024, 150, Periodicita.SETTIMANALE);
        Catalogo rivista3= new Rivista(null, "National Geographic", 2023, 180, Periodicita.MENSILE);
        Catalogo rivista4= new Rivista(null, "Time", 2022, 120, Periodicita.SETTIMANALE);
        Catalogo libro1= new Libro(null, "Il Signore degli Anelli", 2000, 300, "Tolkien", "Fantasy");
        Catalogo libro2= new Libro(null, "Harry Potter", 2001, 250, "J.K. Rowling", "Fantasy");
        Catalogo libro3= new Libro(null, "Gli Indifferenti", 1950, 200, "Alberto Moravia", "Letteratura");
        Catalogo libro4= new Libro(null, "Il Nome della Rosa", 1980, 280, "Umberto Eco", "Letteratura");


        Utente utente1= new Utente(null,"Mario", "Rossi", LocalDate.of(1990, 02, 01));
        Utente utente2= new Utente(null, "Luigi", "Verdi", LocalDate.of(1995, 03, 02));
        Utente utente3= new Utente(null, "Giovanni", "Bianchi", LocalDate.of(1998, 04, 03));
        Utente utente4= new Utente(null, "Paolo", "Neri", LocalDate.of(1992, 05, 04));



        Prestito prestito1= new Prestito(null,utente1,libro1,LocalDate.of(2025,01,01), LocalDate.of(2025, 01, 02) );
        Prestito prestito2= new Prestito(null, utente2, libro2, LocalDate.of(2024, 02, 02), LocalDate.of(2024, 02, 03));
        Prestito prestito3= new Prestito(null, utente1, rivista1, LocalDate.of(2023, 03, 03), LocalDate.of(2023, 03, 04));
        Prestito prestito4= new Prestito(null, utente4, rivista2, LocalDate.of(2022, 04, 04), LocalDate.of(2022, 04, 05));
        Prestito prestito5= new Prestito(null, utente3, libro3, LocalDate.of(2021, 05, 05),  LocalDate.of(2021, 05, 06));


        em.getTransaction().begin();

        //Aggiunta dei singoli elementi nel DB

        catalogoDAO.addElemento(rivista1);
        catalogoDAO.addElemento(rivista2);
        catalogoDAO.addElemento(rivista3);
        catalogoDAO.addElemento(rivista4);
        catalogoDAO.addElemento(libro1);
        catalogoDAO.addElemento(libro2);
        catalogoDAO.addElemento(libro3);
        catalogoDAO.addElemento(libro4);
        utenteDAO.addUtente(utente1);
        utenteDAO.addUtente(utente2);
        utenteDAO.addUtente(utente3);
        utenteDAO.addUtente(utente4);
        prestitoDAO.addPrestito(prestito1);
        prestitoDAO.addPrestito(prestito2);
        prestitoDAO.addPrestito(prestito3);
        prestitoDAO.addPrestito(prestito4);

        System.out.println("**********************************************");
        System.out.println("Benvenuto nel catalogo bibliotecario di Manuel");
        System.out.println("**********************************************");


        System.out.println("Selezionare l'opzione desiderata:");
        System.out.println("1. Ricerca per ISBN");
        System.out.println("2. Ricerca per anno di pubblicazione");
        System.out.println("3. Ricerca per autore");
        System.out.println("4. Ricerca per titolo");
        System.out.println("5. Ricerca degli elementi attualmente in prestito dato un numero di tessera utente");
        System.out.println("6. Ricerca dei prestiti scaduti");
        System.out.println("7. Cancellare un elemento del catalogo");
        System.out.println("0. Esci");
        System.out.println(" ");

        int scelta= scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                System.out.println("Inserire l'ISBN: ");
                long isbn = scanner.nextLong();
                scanner.nextLine();
                catalogoDAO.findByISBN(isbn);
                break;
            case 2:
                System.out.println("Inserire l'anno di pubblicazione");
                String anno = scanner.nextLine();
                catalogoDAO.findByAnnoPubblicazione(anno);
                break;
            case 3:
                System.out.println("Inserire l'ISBN: ");
                 String autore = scanner.nextLine();
                catalogoDAO.findByAutore(autore);
                break;
            case 4:
                System.out.println("Inserire il titolo dell'elemento da ricercare nel catalogo");
                String titolo = scanner.nextLine();
                catalogoDAO.findByAutore(titolo);
                break;
            case 5:
                System.out.println("Inserire il numero di tessera dell'utente");
                long tessera = scanner.nextLong();
                scanner.nextLine();
                catalogoDAO.findByNumeroTesseraUtente(tessera);
                break;
            case 6:
                catalogoDAO.findPrestitiScadutiNonRestituiti();
                break;
            case 7:
                System.out.println("Inserire l'ISBN dell'elemento da cancellare");
                long isbn1 = scanner.nextLong();
                scanner.nextLine();
                catalogoDAO.findByISBN(isbn1);
                break;
            case 0:
                System.out.println("Grazie per aver visitato il catalogo bibliotecario di Manuel");
                System.out.println("Arrivederci!");
                return;
            default:
                System.out.println("Scelta non valida.");


        }





        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
