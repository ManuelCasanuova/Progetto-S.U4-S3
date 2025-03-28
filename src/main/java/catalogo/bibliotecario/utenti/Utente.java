package catalogo.bibliotecario.utenti;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long numeroDiTessera;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String cognome;
    private LocalDate dataDiNascita;

    public Utente(Long numeroDiTessera, String nome, String cognome, LocalDate dataDiNascita) {
        this.numeroDiTessera = numeroDiTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public Utente() {
    }

    public Long getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public void setNumeroDiTessera(Long numeroDiTessera) {
        this.numeroDiTessera = numeroDiTessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
}
