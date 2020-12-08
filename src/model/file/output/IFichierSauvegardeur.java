package model.file.output;

import model.network.trame.ITrame;

import java.util.List;


public interface IFichierSauvegardeur {
    void sauvegarder(List<ITrame> trames);
}
