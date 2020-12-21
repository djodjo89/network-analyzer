package model.network.entete.icmp;

import model.network.champ.hexa.ChampHexa;

public class SequenceNumberLE extends ChampHexa {
    public SequenceNumberLE(String valeur) {
        super("Sequence number (BE)", "00" + valeur);
    }
}
