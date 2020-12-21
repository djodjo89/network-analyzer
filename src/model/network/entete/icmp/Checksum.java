package model.network.entete.icmp;

import model.network.champ.hexa.ChampHexa;

public class Checksum extends ChampHexa {
    public Checksum(String valeur) {
        super("Checksum", valeur);
    }
}
