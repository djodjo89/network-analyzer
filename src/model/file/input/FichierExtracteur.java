package model.file.input;

import java.util.List;
import model.network.Trame;

public interface FichierExtracteur {
    List<Trame> extraireTrames(String nomFichier);
}
