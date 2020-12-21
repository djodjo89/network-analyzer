package model.network.entete.ip;

import model.network.champ.Champ;
import model.network.champ.ListeChamps;
import model.network.entete.Entete;
import model.network.entete.ip.adresse.AdresseIpDestination;
import model.network.entete.ip.adresse.AdresseIpSource;
import model.network.entete.ip.flags.Flags;
import model.network.entete.ip.flags.FragmentOffset;
import model.type.string.HexadecimalString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IP extends Entete {
    private final Protocole protocole;
    private final AdresseIpSource source;
    private final AdresseIpDestination destination;
    private final TimeToLive ttl;

    public IP(List<String> valeurs, int optionsSize) {
        super("Internet Protocol Version 4",
                description(
                        new AdresseIpSource(valeurs.subList(10, 13)),
                        new AdresseIpDestination(valeurs.subList(14, 17))),
                Arrays.asList(
                        new TotalLength(valeurs.get(2) + valeurs.get(3)),
                        new Identification(valeurs.get(4) + valeurs.get(5)),
                        new Flags(valeurs.get(6)),
                        new FragmentOffset(valeurs.get(6) + valeurs.get(7)),
                        new TimeToLive(valeurs.get(8)),
                        new Protocole(valeurs.get(9)),
                        new HeaderChecksum(valeurs.get(10) + valeurs.get(11)),
                        new AdresseIpSource(valeurs.subList(12, 15)),
                        new AdresseIpDestination(valeurs.subList(16, 19)),
                        new ListeChamps("Options",
                                optionsSize > 0
                                        ? Arrays.asList(
                                        new ListeChamps("Record Route", Arrays.asList(
                                                new Champ<>("Type", new HexadecimalString(valeurs.get(20)).getNumericValue()),
                                                new Champ<>("Length", new HexadecimalString(valeurs.get(21)).getNumericValue()),
                                                new Champ<>("Pointer", new HexadecimalString(valeurs.get(22)).getNumericValue())
                                        )),
                                        new ListeChamps("End of Options List", Collections.singletonList(
                                                new Champ<>("Type", 0)
                                        )))
                                        : new ArrayList<>())
                ));
        protocole = new Protocole(valeurs.get(9));
        source = new AdresseIpSource(valeurs.subList(12, 15));
        destination = new AdresseIpDestination(valeurs.subList(16, 19));
        ttl = new TimeToLive(valeurs.get(8));
    }

    public Protocole getIpProtocole() {
        return protocole;
    }

    public AdresseIpSource getSource() {
        return source;
    }

    public AdresseIpDestination getDestination() {
        return destination;
    }

    public TimeToLive getTtl() {
        return ttl;
    }

    private static String description(AdresseIpSource source, AdresseIpDestination destination) {
        return "Src: " + source.getValeur() + ", Dst: " + destination.getValeur();
    }
}
