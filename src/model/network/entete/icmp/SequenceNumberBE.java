package model.network.entete.icmp;

import model.network.champ.hexa.ChampHexa;

public class SequenceNumberBE extends ChampHexa {
    public SequenceNumberBE(String valeur) {
        super("Sequence number (BE)", valeur + "00");
    }
}
