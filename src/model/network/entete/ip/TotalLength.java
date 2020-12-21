package model.network.entete.ip;

import model.network.champ.Champ;
import model.type.string.HexadecimalString;

public class TotalLength extends Champ<Integer> {
    public TotalLength(String valeur) {
        super("Total Length", new HexadecimalString(valeur).getNumericValue());
    }
}
