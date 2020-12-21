package model.network.entete.icmp;

import model.network.champ.Champ;
import model.type.string.HexadecimalString;

public class TypeIcmp extends Champ<Integer> {
    public TypeIcmp(String valeur) {
        super("Type", new HexadecimalString(valeur).getNumericValue());
    }
}
