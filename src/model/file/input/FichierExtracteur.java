package model.file.input;

import java.util.List;
import model.network.trame.ITrame;

public interface FichierExtracteur {
    List<ITrame> extraireTrames(String nomFichier);
}
