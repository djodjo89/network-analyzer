package model.network.champ;

import model.network.INetworkObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListeChamps extends AbstractChamp {
    private String etiquette;
    private List<IChamp> sousChamps;

    public ListeChamps(String etiquette) {
        this.etiquette = etiquette;
        this.sousChamps = new ArrayList<>();
    }

    public ListeChamps(String etiquette, List<IChamp> sousChamps) {
        this.etiquette = etiquette;
        this.sousChamps = sousChamps;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public String getValeur() {
        return sousChamps.toString();
    }

    public boolean addChamp(IChamp champ) {
        boolean res = sousChamps.add(champ);
        champ.setNotRoot();
        return res;
    }

    @Override
    public List<? extends INetworkObject> getChildren() {
        return sousChamps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListeChamps that = (ListeChamps) o;
        boolean sousChampsEqual = true;
        int i = 0;

        while (sousChampsEqual && i < sousChamps.size()) {
            sousChampsEqual = sousChamps.get(i).equals(((ListeChamps) o).sousChamps);
            i++;
        }

        return getEtiquette().equals(that.getEtiquette()) && sousChampsEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEtiquette(), sousChamps);
    }

    @Override
    public String toString() {
        return getEtiquette();
    }
}
