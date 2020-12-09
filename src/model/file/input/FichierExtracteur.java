package model.file.input;

import javafx.util.Pair;
import model.network.champ.Champ;
import model.network.trame.ITrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FichierExtracteur implements IFichierExtracteur {
    private int fromHexaToInt(String number) {
        if (number.length() == 0) return 0;
        String rawValue = String.valueOf(number.charAt(0));
        int valeur = -1;
        int i = 0;
        while (valeur == -1 && i < Hexa.values().length) {
            Hexa currentHexa = Hexa.values()[i];
            if (currentHexa.getValue().equals(rawValue.toUpperCase()))
                valeur = currentHexa.ordinal();
            i++;
        }
        return (int) (Math.pow(16, number.length() - 1) * valeur) + fromHexaToInt(number.substring(1));
    }

    private int fromBitsToInt(String number) {
        if (number.length() == 0) return 0;
        String rawValue = String.valueOf(number.charAt(0));
        int valeur = -1;
        int i = 0;
        while (valeur == -1 && i < Hexa.values().length) {
            Hexa currentHexa = Hexa.values()[i];
            if (currentHexa.getValue().equals(rawValue.toUpperCase()))
                valeur = currentHexa.ordinal();
            i++;
        }
        return (int) (Math.pow(2, number.length() - 1) * valeur) + fromHexaToInt(number.substring(1));
    }

    private String fromHexaToBits(String number) {
        if (number.length() == 0) return "";
        String rawValue = String.valueOf(number.charAt(0));
        int valeur = -1;
        int i = 0;
        while (valeur == -1 && i < Hexa.values().length) {
            Hexa currentHexa = Hexa.values()[i];
            if (currentHexa.getValue().equals(rawValue.toUpperCase()))
                valeur = currentHexa.ordinal();
            i++;
        }
        return (int) (Math.pow(2, number.length() - 1) * valeur) + fromHexaToInt(number.substring(1));
    }

    @Override
    public List<ITrame> extraireTrames(String nomFichier) {
        try {
            String rawContent = Files.readString(Paths.get("test/file/data/trames.txt"));
            List<String> lignes = Arrays.asList(rawContent.split("\n"));

            List<Pair<Integer, List<String>>> contenu = new ArrayList<>();

            for (int i = 0; i < lignes.size(); i++) {
                String[] ligneSplittee = lignes.get(i).split("  ");
                if (ligneSplittee.length != 2) throw new LigneIncompleteException(i + 1);
                int previousOffset = contenu.size() != 0 ? contenu.get(contenu.size() - 1).getKey() : 0;
                int previousLineLength = contenu.size() != 0 ? contenu.get(contenu.size() - 1).getValue().size() : 0;
                int currentOffset = fromHexaToInt(ligneSplittee[0]);
                String[] rawLigneContenu = ligneSplittee[1].split(" ");

                if (previousOffset == 0 || previousOffset + previousLineLength == currentOffset) {
                    String[] prochaineLigneSplittee = i < lignes.size() - 1 ? lignes.get(i + 1).split("  ") : new String[]{"00", ""};
                    int nextOffset = fromHexaToInt(prochaineLigneSplittee[0]);
                    List<String> ligneContenu = new ArrayList<>();
                    if (nextOffset != 0)
                        for (int indexHexa = 0; indexHexa < nextOffset - currentOffset; indexHexa++) {
                            if (rawLigneContenu[indexHexa].length() == 2)
                                ligneContenu.add(rawLigneContenu[indexHexa]);
                            else throw new LigneIncompleteException(i);
                        }
                    else
                        for (String value :
                                rawLigneContenu) {
                            if (value.length() == 2)
                                ligneContenu.add(value);
                            else throw new LigneIncompleteException(i);
                        }
                    contenu.add(new Pair(currentOffset, ligneContenu));
                }
            }

            List<String> valeursTrame = new ArrayList<>();

            for (Pair<Integer, List<String>> paire :
                    contenu) {
                valeursTrame.addAll(paire.getValue());
            }

            List<ITrame> trames = new ArrayList<>();

            Champ macSource = new Champ("Source", IntStream
                    .range(0, 5)
                    .mapToObj(index -> valeursTrame.get(index))
                    .reduce((a, b) -> a + ":" + b)
                    .orElse(""));
            Champ macDestination = new Champ("Destination", IntStream
                    .range(6, 11)
                    .mapToObj(index -> valeursTrame.get(index))
                    .reduce((a, b) -> a + ":" + b)
                    .orElse(""));
            String protocole = Arrays
                    .stream(Protocole.values())
                    .filter(proto -> proto.getValue().equals(String.valueOf(valeursTrame.get(14).charAt(0))))
                    .findFirst()
                    .orElse(Protocole.IPV4)
                    .getName();
            Champ totalLength = new Champ("Total Length", fromHexaToInt(valeursTrame.get(16) + valeursTrame.get(17)));
            Champ identification = new Champ("Identification", "0x" + valeursTrame.get(18) + valeursTrame.get(19));
            Champ flags = new Champ("Flags", "0x" + valeursTrame.get(20) + valeursTrame.get(21));
            Champ fragmentOffset = new Champ("Fragment offset", fromBitsToInt(valeursTrame.get(20).substring(12)));

            System.out.println(macSource);
            System.out.println(macDestination);
            System.out.println(protocole);
            System.out.println(totalLength);
            System.out.println(identification);

        } catch (IOException | LigneIncompleteException e) {
            e.printStackTrace();
        }

        return null;
    }
}
