package model.network.entete.icmp;

import model.network.champ.hexa.ChampHexa;

public class IdentifierBE extends ChampHexa {
    public IdentifierBE(String valeur) {
        super("Identifier (BE)", valeur + "00");
    }
}
