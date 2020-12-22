package model.network.entete.ip.flags;

import model.type.numeric.BinaireNumeric;
import model.type.string.HexadecimalString;

public class FlagsProcessor {
    static String firstFragmentBinaryString(String valeurBrute) {
        int firstFragmentDecimalValue = new HexadecimalString(valeurBrute).getNumericValue();
        String res = new BinaireNumeric(firstFragmentDecimalValue).toString();
        int i = 0;
        while (i < 13 && res.length() < 13) {
            res = "0" + res;
            i++;
        }
        return res;
    }

    static int offset(String valeurBrute) {
        int offset = 0;
        switch (firstFragmentBinaryString(valeurBrute).length()) {
            case 6:
                offset = 1;
                break;
            case 7:
                offset = 2;
                break;
            case 8:
                offset = 3;
                break;
            default:
        }
        return offset;
    }
}
