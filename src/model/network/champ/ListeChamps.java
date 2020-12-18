package model.network.champ;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
