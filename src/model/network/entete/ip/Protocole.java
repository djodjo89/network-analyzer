package model.network.entete.ip;

import model.network.champ.Champ;
import model.type.string.HexadecimalString;

public class Protocole extends Champ<Integer> {
    public Protocole(String valeur) {
        super("Protocole", new HexadecimalString(valeur).getNumericValue());
    }
}
