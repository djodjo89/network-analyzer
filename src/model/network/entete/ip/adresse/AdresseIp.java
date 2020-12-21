package model.network.entete.ip.adresse;

import model.network.champ.compose.ChampCompose;
import model.type.string.HexadecimalString;

import java.util.List;

public class AdresseIp extends ChampCompose {
    public AdresseIp(String etiquette, List<String> valeurs) {
        super(etiquette, valeurs, ".", val -> String.valueOf(new HexadecimalString(val).getNumericValue()));
    }
}
