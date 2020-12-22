package model.network.entete.ip.flags;

import model.network.champ.hexa.ChampHexa;
import model.type.string.HexadecimalString;

import static model.network.entete.ip.flags.FlagsProcessor.firstFragmentBinaryString;
import static model.network.entete.ip.flags.FlagsProcessor.offset;

public class Flags extends ChampHexa {
    public Flags(String valeur) {
        super("Flags", extractFlags(valeur));
    }

    private static String extractFlags(String valeurBrute) {
        String firstFragmentBinaryString = firstFragmentBinaryString(valeurBrute);
        int offset = offset(valeurBrute);
        return new HexadecimalString(
                offset != 0
                        ? firstFragmentBinaryString.substring(0, offset)
                        : firstFragmentBinaryString
        ).toString();
    }
}
