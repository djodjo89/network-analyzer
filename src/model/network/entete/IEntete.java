package model.network.entete;

import model.network.champ.IChamp;

import java.util.List;

public interface IEntete {
    String getProtocole();
    String getDescription();
    List<IChamp> getChamps();
}
