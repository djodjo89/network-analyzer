package model.network.champ;

import java.util.ArrayList;
import java.util.List;

public class ListeChamps extends AbstractChamp<List<IChamp>> {
    private String etiquette;
    private List<IChamp> sousChamps;

    public ListeChamps(String etiquette) {
        this.etiquette = etiquette;
        this.sousChamps = new ArrayList<>();
    }

    public String getEtiquette() {
        return etiquette;
    }

    public List<IChamp> getValeur() {
        return sousChamps;
    }

    public boolean addChamp(IChamp champ) {
        boolean res = sousChamps.add(champ);
        champ.setNotRoot();
        return res;
    }

    public int getHauteur() {
        return 1 + sousChamps
                .stream()
                .map(IChamp::getHauteur)
                .reduce(Integer::max)
                .orElse(0);
    }
}
