package model.network.entete.ip.flags;

import model.network.champ.hexa.ChampHexa;
import model.type.string.HexadecimalString;

public class Flags extends ChampHexa {
    public Flags(String valeur) {
        super("Flags", extractFlags(valeur));
    }

    private static String extractFlags(String valeurBrute) {
        String firstFragmentBinaryString = FlagsProcessor.firstFragmentBinaryString(valeurBrute);
        int offset = FlagsProcessor.offset(valeurBrute);
        return new HexadecimalString(
                offset != 0
                        ? firstFragmentBinaryString.substring(0, offset)
                        : firstFragmentBinaryString
        ).toString();
    }
}
