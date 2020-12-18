package model.file.input;

import javafx.util.Pair;
import model.network.champ.Champ;
import model.network.champ.IChamp;
import model.network.champ.ListeChamps;
import model.network.entete.Entete;
import model.network.entete.IEntete;
import model.network.trame.ITrame;
import model.network.trame.Trame;
import model.type.Hexa;
import model.type.numeric.BinaireNumeric;
import model.type.string.BinaireString;
import model.type.string.HexadecimalString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FichierExtracteur implements IFichierExtracteur {

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
        int currentOffset = new HexadecimalString(ligneSplittee[0]).getNumericValue();
        String[] rawLigneContenu = ligneSplittee[1].split(" ");

        if (previousOffset == 0 || previousOffset + previousLineLength == currentOffset) {
          String[] prochaineLigneSplittee = i < lignes.size() - 1 ? lignes.get(i + 1).split("  ") : new String[]{ "00"
                  , "" };
          int nextOffset = new HexadecimalString(prochaineLigneSplittee[0]).getNumericValue();
          List<String> ligneContenu = new ArrayList<>();
          if (nextOffset != 0) for (int indexHexa = 0; indexHexa < nextOffset - currentOffset; indexHexa++) {
            if (rawLigneContenu[indexHexa].length() == 2) ligneContenu.add(rawLigneContenu[indexHexa]);
            else throw new LigneIncompleteException(i);
          }
          else for (String value : rawLigneContenu) {
            if (value.length() == 2) ligneContenu.add(value);
            else throw new LigneIncompleteException(i);
          } contenu.add(new Pair(currentOffset, ligneContenu));
        }
      }

      List<String> valeursTrame = new ArrayList<>();

      for (Pair<Integer, List<String>> paire : contenu) {
        valeursTrame.addAll(paire.getValue());
      }

      List<ITrame> trames = new ArrayList<>();

      Champ macSource = new Champ("Source",
                                  IntStream.range(0, 5).mapToObj(index -> valeursTrame.get(index)).reduce((a, b) -> a + ":" + b).orElse(""));
      Champ macDestination = new Champ("Destination",
                                       IntStream.range(6, 11).mapToObj(index -> valeursTrame.get(index)).reduce((a,
                                                                                                                 b) -> a + ":" + b).orElse(""));
      Champ type = new Champ("Type", "0x" + new HexadecimalString(valeursTrame.get(12) + valeursTrame.get(13)));

      List<IChamp> champsEthernet = new ArrayList<>(Arrays.asList(macDestination, macSource, type));

      Entete ethernet = new Entete("Ethernet II", "Src: Oracle_" + macSource.toString().substring(macSource.toString().length() / 2) + " (" + macSource + "), Dst: Oracle_" + macDestination.toString().substring(macDestination.toString().length() / 2) + " (" + macDestination +")", champsEthernet);


      String protocole =
              Arrays.stream(Protocole.values()).filter(proto -> proto.getValue().equals(String.valueOf(valeursTrame.get(14).charAt(0)))).findFirst().orElse(Protocole.IPV4).getName();
      Champ totalLength = new Champ("Total Length", new HexadecimalString(valeursTrame.get(16) + valeursTrame.get(17)).getNumericValue());
      Champ identification = new Champ("Identification", "0x" + valeursTrame.get(18) + valeursTrame.get(19));

      int firstFragmentDecimalValue = new HexadecimalString(valeursTrame.get(20)).getNumericValue();
      String firstFragmentBinaryString = new BinaireNumeric(firstFragmentDecimalValue).toString();
      int offset = 0;
      switch (firstFragmentBinaryString.length()) {
        case 6:
          offset = 1; break;
        case 7:
          offset = 2; break;
        case 8:
          offset = 3; break;
        default:
          offset = 0;
      }
      String flagsDisplay = "0x" + new HexadecimalString(
              offset != 0
              ? firstFragmentBinaryString.substring(0, offset)
              : firstFragmentBinaryString
              ).toString();

      Champ flags = new Champ("Flags", flagsDisplay);

      String secondFragmentBinaryString = firstFragmentBinaryString.substring(offset);
      BinaireString firstPart = new BinaireString(secondFragmentBinaryString);
      BinaireString secondPart = new BinaireString(valeursTrame.get(21));
      BinaireString fragmentOffsetString = new BinaireString(firstPart.toString() + secondPart.toString());
      Champ fragmentOffset = new Champ("Fragment offset", fragmentOffsetString.getNumericValue());
      Champ ttl = new Champ("Time to live", new HexadecimalString(valeursTrame.get(22)).getNumericValue());
      Champ ipProtocole = new Champ("Protocole", new HexadecimalString(valeursTrame.get(23)).getNumericValue());
      Champ headerChecksum = new Champ("Header cheksum", "0x" + valeursTrame.get(24) + valeursTrame.get(25));
      Champ ipSource = new Champ("Source",
                                 new HexadecimalString(valeursTrame.get(26)).getNumericValue() + "." +
                                 new HexadecimalString(valeursTrame.get(27)).getNumericValue() + "." +
                                 new HexadecimalString(valeursTrame.get(28)).getNumericValue() + "." +
                                 new HexadecimalString(valeursTrame.get(29)).getNumericValue()
      );
      Champ ipDestination = new Champ("Destination",
                                   new HexadecimalString(valeursTrame.get(30)).getNumericValue() + "." +
                                   new HexadecimalString(valeursTrame.get(31)).getNumericValue() + "." +
                                   new HexadecimalString(valeursTrame.get(32)).getNumericValue() + "." +
                                   new HexadecimalString(valeursTrame.get(33)).getNumericValue()
      );
      ListeChamps options = new ListeChamps("Options");
      ListeChamps recordRoute = new ListeChamps("Record Route");
      Champ recordRouteType = new Champ("type", new HexadecimalString(valeursTrame.get(34)));
      Champ length = new Champ("Length", new HexadecimalString(valeursTrame.get(35)));
      Champ pointer = new Champ("Pointer", new HexadecimalString(valeursTrame.get(36)));

      recordRoute.addChamp(recordRouteType);
      recordRoute.addChamp(length);
      recordRoute.addChamp(pointer);
      options.addChamp(recordRoute);

      List<IChamp> ipv4Champs = Arrays.asList(
              totalLength,
              identification,
              flags,
              fragmentOffset,
              ttl,
              ipProtocole,
              headerChecksum,
              ipSource,
              ipDestination,
              options
      );

      Entete ipv4 = new Entete("Internet Protocol Version 4", "Src: " + ipSource.getValeur() + ", Dst: " + ipDestination.getValeur(), ipv4Champs);

      Champ typeIcmp = new Champ("Type", new HexadecimalString(valeursTrame.get(74)).getNumericValue());
      Champ code = new Champ("Code", new HexadecimalString(valeursTrame.get(75)).getNumericValue());
      Champ checksum = new Champ("Checksum", "0x" + new HexadecimalString(valeursTrame.get(76) + valeursTrame.get(77)));
      Champ identifierBE = new Champ("Identifier (BE)", "0x" + new HexadecimalString(valeursTrame.get(78) + "00"));
      Champ identifierLE = new Champ("Identifier (LE)", "0x00" + new HexadecimalString(valeursTrame.get(79)));
      Champ sequenceNumberBE = new Champ("Sequence number (BE)", "0x" + new HexadecimalString(valeursTrame.get(80) + "00"));
      Champ sequenceNumberLE = new Champ("Sequence number (LE)", "0x00" + new HexadecimalString(valeursTrame.get(81)));
      Champ data = new Champ("Data", new HexadecimalString("29368c410003862b08090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f202122232425262728292a2b2c2d2e2f3031323334353637"));

      List<IChamp> icmpChamps = Arrays.asList(
              typeIcmp,
              code,
              checksum,
              identifierBE,
              identifierLE,
              sequenceNumberBE,
              sequenceNumberLE,
              data
      );

      Entete icmp = new Entete("Internet Control Message Protocol", "", icmpChamps);

      List<IEntete> entetes = new ArrayList<>(Arrays.asList(
              ethernet,
              ipv4,
              icmp
      ));

      ITrame trame1 = new Trame(
              // A incrementer
              1,
              0.000000,
              ipSource.toString(),
              ipDestination.toString(),
              "ICMP",
              "Echo (ping) request  id=0x" + identifierBE.toString() + identifierLE.toString() + ", seq=" + sequenceNumberBE.toString() + "/" + sequenceNumberLE.toString() + ", ttl=" + ttl.toString() + " (no response found!)",
              entetes,
              "29 36 8c 41 00 03 86 2b 08 09 0a 0b 0c 0d 0e 0f 10 11 12 13 14 15 16 17 18 19 1a 1b 1c 1d 1e 1f 20 21 22 23 24 25 26 27 28 29 2a 2b 2c 2d 2e 2f 30 31 32 33 34 35 36 37"
      );

      /*System.out.println(macSource);
      System.out.println(macDestination);
      System.out.println(protocole);
      System.out.println(totalLength);
      System.out.println(identification);
      System.out.println(flags);
      System.out.println(fragmentOffset);
      System.out.println(ttl);
      System.out.println(ipProtocole);
      System.out.println(headerChecksum);
      System.out.println(ipSource);
      System.out.println(ipDestination);
      System.out.println(typeIcmp);
      System.out.println(code);
      System.out.println(checksum);
      System.out.println(identifierBE);
      System.out.println(identifierLE);
      System.out.println(sequenceNumberBE);
      System.out.println(sequenceNumberLE);*/

      return new ArrayList<>(Collections.singletonList(trame1));

    } catch (IOException | LigneIncompleteException e) {
      e.printStackTrace();
    }

    return null;
  }
}
