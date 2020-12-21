package model.network.entete.ethernet.mac;

import model.network.champ.compose.ChampCompose;

import java.util.List;

public class AdresseMac extends ChampCompose {
    public AdresseMac(String etiquette, List<String> valeurs) {
        super(etiquette, valeurs, ":");
    }
}
