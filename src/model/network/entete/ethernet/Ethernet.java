package model.network.entete.ethernet;

import model.network.entete.Entete;
import model.network.entete.icmp.TypeIcmp;
import model.network.entete.ethernet.mac.AdresseMacDestination;
import model.network.entete.ethernet.mac.AdresseMacSource;

import java.util.Arrays;
import java.util.List;

import static model.network.champ.compose.ChampCompose.extraireAdresse;

public class Ethernet extends Entete {
    public Ethernet(List<String> valeursTrame) {
        super("Ethernet II",
                Ethernet.description(
                        new AdresseMacSource(extraireAdresse(0, 5, valeursTrame)),
                        new AdresseMacDestination(extraireAdresse(6, 11, valeursTrame))),
                Arrays.asList(
                    new AdresseMacDestination(extraireAdresse(6, 11, valeursTrame)),
                    new AdresseMacSource(extraireAdresse(0, 5, valeursTrame)),
                    new TypeEthernet(valeursTrame.get(12) + valeursTrame.get(13))));
    }

    private static String description(AdresseMacSource source, AdresseMacDestination destination) {
        return "Src: " + source.toString().substring(source.toString().length() / 2) + " (" + source + "), " +
                "Dst: " + destination.toString().substring(destination.toString().length() / 2) + " (" + destination + ")";
    }
}
