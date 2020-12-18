package model.network.entete;

import model.network.champ.IChamp;
import model.network.trame.ITrame;

import java.util.List;

public interface IEntete {
    String getProtocole();
    String getDescription();
    List<IChamp> getChamps();
    boolean equals(Object o);
}
