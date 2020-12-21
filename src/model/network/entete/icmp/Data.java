package model.network.entete.icmp;

import model.network.champ.Champ;

import java.util.List;

public class Data extends Champ<String> {
    public Data(List<String> valeurs) {
        super("Data", formatValues(valeurs));
    }

    private static String formatValues(List<String> valeurs) {
        return valeurs
                .stream()
                .reduce("", (val1, val2) -> val1 + " " + val2);
    }
}
