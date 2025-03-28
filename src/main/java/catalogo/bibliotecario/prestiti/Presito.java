package catalogo.bibliotecario.prestiti;

import catalogo.bibliotecario.cataloghi.Catalogo;
import catalogo.bibliotecario.utenti.Utente;
import jakarta.persistence.Entity;

import java.time.LocalDate;


@Entity

public class Presito {

    private Utente utente;
    private Catalogo catalogo;
    private LocalDate dataInizioPrestito;
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;

    public Presito(Utente utente, LocalDate dataRestituzioneEffettiva, LocalDate dataRestituzionePrevista, LocalDate dataInizioPrestito, Catalogo catalogo) {
        this.utente = utente;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
        this.dataRestituzionePrevista = dataInizioPrestito.plus(30);
        this.dataInizioPrestito = dataInizioPrestito;
        this.catalogo = catalogo;
    }
}
