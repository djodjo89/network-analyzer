package model.file.input;

import java.util.List;
import model.network.trame.ITrame;

public interface IFichierExtracteur {
    List<ITrame> extraireTrames(String nomFichier);
}
