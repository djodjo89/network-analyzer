package model.network.entete.icmp;

import model.network.champ.hexa.ChampHexa;

public class IdentifierLE extends ChampHexa {
    public IdentifierLE(String valeur) {
        super("Identifier (LE)", "00" + valeur);
    }
}
