package model.network.entete.ip;

import model.network.champ.hexa.ChampHexa;

public class HeaderChecksum extends ChampHexa {
    public HeaderChecksum(String valeur) {
        super("Header checksum", valeur);
    }
}
