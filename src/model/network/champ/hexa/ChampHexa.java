package model.network.champ.hexa;

import model.network.champ.Champ;
import model.type.string.HexadecimalString;

public class ChampHexa extends Champ<String> {
    public ChampHexa(String etiquette, String valeur) {
        super(etiquette, "0x" + new HexadecimalString(valeur));
    }
}
