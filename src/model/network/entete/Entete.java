package model.network.entete;

import model.network.champ.IChamp;

import java.util.List;

public class Entete implements IEntete {
    private final String protocole;
    private final String description;
    private final List<IChamp> champs;

    public Entete(String protocole, String description, List<IChamp> champs) {
        this.protocole = protocole;
        this.description = description;
        this.champs = champs;
    }

    @Override
    public String getProtocole() {
        return protocole;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<IChamp> getChamps() {
        return champs;
    }
}
