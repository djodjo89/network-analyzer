package model.network.entete.icmp;

import model.network.champ.Champ;
import model.type.string.HexadecimalString;

public class Code extends Champ<Integer> {
    public Code(String valeur) {
        super("Code", new HexadecimalString(valeur).getNumericValue());
    }
}
