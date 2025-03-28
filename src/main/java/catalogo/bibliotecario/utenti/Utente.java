package catalogo.bibliotecario.utenti;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long numeroDiTessera;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String cognome;
    private LocalDate dataDiNascita;

}
