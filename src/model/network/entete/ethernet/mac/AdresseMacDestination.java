package model.network.entete.ethernet.mac;

import model.network.entete.ethernet.mac.AdresseMac;

import java.util.List;

public class AdresseMacDestination extends AdresseMac {
    public AdresseMacDestination(List<String> valeur) {
        super("Destination", valeur);
    }
}
