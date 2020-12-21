package model.file.input;

import model.network.champ.Champ;
import model.network.champ.IChamp;
import model.network.champ.ListeChamps;
import model.network.entete.Entete;
import model.network.entete.IEntete;
import model.network.entete.ethernet.Ethernet;
import model.network.entete.icmp.Icmp;
import model.network.entete.ip.IP;
import model.network.trame.ITrame;
import model.network.trame.Trame;
import model.type.string.HexadecimalString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FichierExtracteur implements IFichierExtracteur {

    private static int optionsSize(List<String> valeurs) {
        System.out.println(valeurs.get(35));
        return new HexadecimalString(valeurs.get(35)).getNumericValue();
    }

    private ITrame process(int no, List<String> valeursTrame) {
        List<String> valeursEthernet = valeursTrame.subList(0, 14);
        List<String> valeursIp = valeursTrame.subList(15, 35);
        int ipOptionsSize = optionsSize(valeursTrame);
        List<String> valeursIcmp = valeursTrame.subList(35 + ipOptionsSize, valeursTrame.size());

        Ethernet ethernet = new Ethernet(valeursEthernet);
        IP ip = new IP(valeursIp, ipOptionsSize);
        System.out.println(valeursIp);
        System.out.println(ip.getIpProtocole());
        System.out.println(ip.getProtocoleAbreviation());
        Icmp icmp = new Icmp(valeursIcmp);

        Protocole protocole = Arrays
                .stream(Protocole.values())
                .filter(proto -> proto.getValue() == ip.getIpProtocole().getValeurDecimale())
                .findAny()
                .orElse(Protocole.ICMP);

        String description =
                "id=" + icmp.getIdentifierBE().getValeur() +
                "/" + icmp.getIdentifierLE().getValeur() +
                ", seq=" + icmp.getSequenceNumberBE().getValeur() +
                "/" + icmp.getSequenceNumberLE().getValeur() +
                ", ttl=" + ip.getTtl().getValeur();

        List<IEntete> entetes = new ArrayList<>(Arrays.asList(
                ethernet,
                ip,
                icmp
        ));

        return new Trame(
                no,
                ip.getSource().getValeur(),
                ip.getDestination().getValeur(),
                protocole.getAbreviation(),
                description,
                entetes,
                icmp.getData().getValeur(),
                valeursTrame.size()
        );
    }

    @Override
    public List<ITrame> extraireTrames(String nomFichier) throws LigneMalFormatteeException {
        List<ITrame> trames = new ArrayList<>();

        try {
            String rawContent = Files.readString(Paths.get(nomFichier)).replaceAll("(?m)^[ \t]*\r?\n", "");
            List<String> lignes = Arrays.asList(rawContent.split("\n"));
            int indiceTrame = 1;
            int indiceLigne = 0;

            while (indiceLigne < lignes.size()) {
                boolean trameFinie = false;
                List<String> valeursTrame = new ArrayList<>();
                int previousOffset = 0;
                int nextOffset = -1;

                while (!trameFinie && indiceLigne < lignes.size()) {
                    try {
                        nextOffset = new HexadecimalString(lignes.get(indiceLigne + 1).split("  ")[0]).getNumericValue();
                        String[] valeursLignes = lignes.get(indiceLigne).split("  ")[1].split(" ");

                        if (nextOffset - previousOffset > valeursLignes.length + valeursTrame.size())
                            throw new LigneMalFormatteeException(indiceLigne);

                        for (int indiceValeur = 0; indiceValeur < nextOffset - previousOffset; indiceValeur++)
                            valeursTrame.add(valeursLignes[indiceValeur]);

                        previousOffset = nextOffset;

                        if (nextOffset == 0) {
                            trameFinie = true;
                            valeursTrame.addAll(Arrays.asList(valeursLignes));
                        }
                    } catch (LigneMalFormatteeException ligneMalFormatteeException) {
                        throw ligneMalFormatteeException;
                    } catch (Exception ignored) {
                    }

                    indiceLigne++;
                }

                try {
                    trames.add(process(indiceTrame, valeursTrame));
                } catch (Exception e) {
                    throw e;
                }
                indiceTrame++;
            }

            return trames;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trames;
    }
}
