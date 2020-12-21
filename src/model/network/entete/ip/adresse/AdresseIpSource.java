package model.network.entete.ip.adresse;

import model.network.entete.ip.adresse.AdresseIp;

import java.util.List;

public class AdresseIpSource extends AdresseIp {
    public AdresseIpSource(List<String> valeurs) {
        super("Source", valeurs);
    }
}
