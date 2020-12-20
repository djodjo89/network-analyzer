package model.network.champ;

import model.network.INetworkObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListeChamps<T> extends AbstractChamp<List<IChamp<T>>> {
    private String etiquette;
    private List<IChamp<T>> sousChamps;

    public ListeChamps(String etiquette) {
        this.etiquette = etiquette;
        this.sousChamps = new ArrayList<>();
    }

    public ListeChamps(String etiquette, List<IChamp<T>> sousChamps) {
        this.etiquette = etiquette;
        this.sousChamps = sousChamps;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public List<IChamp<T>> getValeur() {
        return sousChamps;
    }

    public boolean addChamp(IChamp<T> champ) {
        boolean res = sousChamps.add(champ);
        champ.setNotRoot();
        return res;
    }

    @Override
    public List<? extends INetworkObject> getChildren() {
        return sousChamps;
    }

    @Override
    public String getValue() {
        return etiquette;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListeChamps<T> that = (ListeChamps<T>) o;
        boolean sousChampsEqual = true;
        int i = 0;

        while (sousChampsEqual && i < sousChamps.size()) {
            sousChampsEqual = sousChamps.get(i).equals(((ListeChamps<T>) o).sousChamps);
            i++;
        }

        return getEtiquette().equals(that.getEtiquette()) && sousChampsEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEtiquette(), sousChamps);
    }

    /*@Override
    public String toString() {return sousChamps
                .stream()
                .map(ssC -> "\t\t" + ssC.getEtiquette() + " : " + ssC.toString())
                .reduce("", (c1, c2) -> c1 + '\n' + c2);
    }*/
}
