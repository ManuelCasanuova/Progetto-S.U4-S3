package catalogo.bibliotecario.cataloghi.riviste;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
public class Rivista {

    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;
}
