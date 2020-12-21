package model.network.entete;

import model.network.INetworkObject;
import model.network.NetworkObject;
import model.network.champ.IChamp;

import java.util.List;
import java.util.Objects;

public class Entete extends IEntete {
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
    public String getProtocoleAbreviation() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entete entete = (Entete) o;
        boolean champsEqual = true;
        int i = 0;

        while (champsEqual && i < getChamps().size()) {
            champsEqual = getChamps().get(i).equals(((Entete) o).getChamps());
            i++;
        }

        return getProtocole().equals(entete.getProtocole()) && getDescription().equals(entete.getDescription()) &&
                champsEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProtocole(), getDescription(), getChamps());
    }

    @Override
    public String toString() {
        return protocole + (!description.equals("") ? ", " + description : "");
    }

    @Override
    public List<? extends INetworkObject> getChildren() {
        return champs;
    }

    @Override
    public String getValeur() {
        return protocole;
    }

    @Override
    public boolean isRoot() {
        return true;
    }
}
