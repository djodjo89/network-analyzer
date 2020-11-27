package model.file.output;

import model.network.Trame;

import java.util.List;

public interface FichierSauvegardeur {
    void sauvegarder(List<Trame> trames);
}
