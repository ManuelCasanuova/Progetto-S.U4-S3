package catalogo.bibliotecario.cataloghi;

import jakarta.persistence.*;

import java.util.UUID;

@Entity

public abstract class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID isbn;

    @Column(length = 100, nullable = false)
    private String titolo;

    @Column(length = 50)
    private String annoPubblicazione;

    private int numeroPagine;
}
