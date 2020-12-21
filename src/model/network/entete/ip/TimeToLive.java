package model.network.entete.ip;

import model.network.champ.Champ;
import model.type.string.HexadecimalString;

public class TimeToLive extends Champ<Integer> {
    public TimeToLive(String valeur) {
        super("Time to live", new HexadecimalString(valeur).getNumericValue());
    }
}
