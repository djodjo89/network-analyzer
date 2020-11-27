package model.network;

import java.util.List;

public interface Entete {
    String getProtocoleComplet();
    String getDescription();
    List<Champ> getChamps();
}
