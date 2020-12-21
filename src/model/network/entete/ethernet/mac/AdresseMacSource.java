package model.network.entete.ethernet.mac;

import model.network.entete.ethernet.mac.AdresseMac;

import java.util.List;

public class AdresseMacSource extends AdresseMac {
    public AdresseMacSource(List<String> valeur) {
        super("Source", valeur);
    }
}
