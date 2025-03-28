package catalogo.bibliotecario.cataloghi.libri;


import catalogo.bibliotecario.cataloghi.Catalogo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Libro extends Catalogo {

    @Column(length = 100, nullable = false)
    private String autore;

    @Column(length = 100, nullable = false)
    private String genere;
}
