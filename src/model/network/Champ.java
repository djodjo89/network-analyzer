package model.network;

import java.util.List;

public interface Champ {
    String getEtiquette();
    int getValeur();
    List<Champ> getSousChamps();
}
