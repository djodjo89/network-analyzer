package model.network.entete.ip.flags;

import model.network.champ.Champ;
import model.type.string.BinaireString;

import static model.network.entete.ip.flags.FlagsProcessor.firstFragmentBinaryString;
import static model.network.entete.ip.flags.FlagsProcessor.offset;

public class FragmentOffset extends Champ<Integer> {
    public FragmentOffset(String valeur) {
        super("Fragment offset", extractValue(valeur));
    }

    private static Integer extractValue(String valeurBrute) {
        int offset = offset(valeurBrute);
        String firstFragmentBinaryString = firstFragmentBinaryString(valeurBrute.substring(0, 2));

        System.out.println("valeur brute " + valeurBrute);
        System.out.println("offset " + offset);
        System.out.println("First fgmt binary string " + firstFragmentBinaryString);

        String secondFragmentBinaryString = firstFragmentBinaryString.substring(offset);
        BinaireString firstPart = new BinaireString(secondFragmentBinaryString);
        BinaireString secondPart = new BinaireString(valeurBrute.substring(3));
        BinaireString fragmentOffsetString = new BinaireString(firstPart.toString() + secondPart.toString());
        int res = 0;
        try {
            res = fragmentOffsetString.getNumericValue();
        } catch(NumberFormatException numberFormatException) {
            System.out.println(valeurBrute);
        }
        return res;
    }
}
