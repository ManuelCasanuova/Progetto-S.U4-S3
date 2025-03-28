package catalogo.bibliotecario.cataloghi.libri;


import catalogo.bibliotecario.cataloghi.Catalogo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Libro extends Catalogo {

    @Column(length = 100)
    private String autore;

    @Column(length = 100)
    private String genere;


    public Libro(Long isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString()  + " " +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", tipologia='" + "Libro" + '\'' ;
    }
}
