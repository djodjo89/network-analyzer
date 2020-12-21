package model.file.output;

import model.network.trame.ITrame;

import java.util.List;


public interface IFichierSauvegardeur {
    void sauvegarder(String path, List<ITrame> trames);
}
