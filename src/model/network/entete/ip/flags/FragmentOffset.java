package model.network.entete.ip.flags;

import model.network.champ.Champ;
import model.type.numeric.BinaireNumeric;
import model.type.string.BinaireString;
import model.type.string.HexadecimalString;

public class FragmentOffset extends Champ<Integer> {
    public FragmentOffset(String valeur) {
        super("Fragment offset", extractValue(valeur));
    }

    private static Integer extractValue(String valeurBrute) {
        HexadecimalString hexadecimalString = new HexadecimalString(valeurBrute);
        int valeurBruteDecimal = hexadecimalString.getNumericValue();
        BinaireNumeric binaireNumeric = new BinaireNumeric(valeurBruteDecimal);
        String binaireString = binaireNumeric.toString();
        int i = 0;
        while (i < 16 && binaireString.length() < 16) {
            binaireString = "0" + binaireString;
            i++;
        }
        binaireString = binaireString.substring(3);
        int value = new BinaireString(binaireString).getNumericValue();

        return value;
    }
}
