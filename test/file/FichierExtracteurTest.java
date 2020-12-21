package file;

import model.file.input.FichierExtracteur;
import model.file.input.LigneMalFormatteeException;
import model.network.champ.Champ;
import model.network.champ.IChamp;
import model.network.entete.Entete;
import model.network.entete.IEntete;
import model.network.trame.ITrame;
import model.network.trame.Trame;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class FichierExtracteurTest {
    @Test
    public void testExtraction() throws LigneMalFormatteeException {
        FichierExtracteur fichierExtracteur = new FichierExtracteur();

        Champ macDestination = new Champ("Destination", "08:00:20:0a:ac:96");
        Champ macSource = new Champ("Source", "08:00:20:0a:70:66");
        Champ type = new Champ("Type", "0x0800");
        List<IChamp> champsEthernet = new ArrayList<>(Arrays.asList(macDestination, macSource, type));

        Entete ethernet = new Entete("Ethernet II", "Src: Oracle_0a:70:66 (08:00:20:0a:70:66), Dst: Oracle_0a:ac:96 (08:00:20:0a:ac:96)", champsEthernet);

        Champ differentiatedServicesField = new Champ("Differentiated Services Field", "0x00");
        Champ totalLength = new Champ("Total Length", 124);
        Champ identification = new Champ("Identification", "0xcbc9");
        Champ flags = new Champ("Flags", "0x0000");
        Champ fragmentOffset = new Champ("Fragment offset", 0);
        Champ timeToLive = new Champ("Time to live", 255);
        Champ protocole = new Champ("Protocole", 1);
        Champ headerChecksum = new Champ("Header checksum", "0xb97f");
        Champ ipSource = new Champ("Source", "132.227.61.5");
        Champ ipDestination = new Champ("Destination", "192.33.159.6");

        IChamp options = new Champ<>("Options", Arrays.asList(
                new Champ<>("Record Route", Arrays.asList(
                        new Champ<>("Type", 999),
                        new Champ<>("Length", 39),
                        new Champ<>("Pointer", 4)
                )),
                new Champ<>("End of Options List", Arrays.asList(
                        new Champ<>("Type", 0)
                ))
        ));
        /*ListeChamps options = new ListeChamps("Options");
        ListeChamps recordRoute = new ListeChamps("Record Route");
        Champ recordRouteType = new Champ("Type", 7);
        Champ length = new Champ("Length", 39);
        Champ pointer = new Champ("Pointer", 4);
        recordRoute.addChamp(recordRouteType);
        recordRoute.addChamp(length);
        recordRoute.addChamp(pointer);
        ListeChamps endOfOptionsList = new ListeChamps("End of Options List");
        Champ endOfOptionListType = new Champ("Type", 0);
        endOfOptionsList.addChamp(endOfOptionListType);
        options.addChamp(recordRoute);
        options.addChamp(endOfOptionsList);*/

        List<IChamp> ipv4Champs = Arrays.asList(
                differentiatedServicesField,
                totalLength,
                identification,
                flags,
                fragmentOffset,
                timeToLive,
                protocole,
                headerChecksum,
                ipSource,
                ipDestination,
                options
        );
        Entete ipv4 = new Entete("Internet Protocol Version 4", "Src: 132.227.61.5, Dst: 192.33.159.6", ipv4Champs);

        Champ typeIcmp = new Champ("Type", 8);
        Champ code = new Champ("Code", 0);
        Champ checksum = new Champ("Checksum", "0xa256");
        Champ identifierBE = new Champ("Identifier (BE)", "0x2f00");
        Champ identifierLE = new Champ("Identifier (LE)", "0x002f");
        Champ sequenceNumberBE = new Champ("Sequence number (BE)", "0x0000");
        Champ sequenceNumberLE = new Champ("Sequence number (LE)", "0x0000");
        Champ data = new Champ("Data", "29368c410003862b08090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f202122232425262728292a2b2c2d2e2f3031323334353637");

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
                1,
                "132.227.61.5",
                "192.33.159.6",
                "ICMP",
                "Echo (ping) request  id=0x2f00, seq=0/0, ttl=255 (no response found!)",
                entetes,
                "08 00 20 0a ac 96 08 00 20 0a 70 66 08 00 4f 00 00 7c cb c9 00 00 ff 01 b9 7f 84 e3 3d 05 c0 21 9f 06 07 27 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 00 a2 56 2f 00 00 00 29 36 8c 41 00 03 86 2b 08 09 0a 0b 0c 0d 0e 0f 10 11 12 13 14 15 16 17 18 19 1a 1b 1c 1d 1e 1f 20 21 22 23 24 25 26 27 28 29 2a 2b 2c 2d 2e 2f 30 31 32 33 34 35 36 37",
                62
        );

        List<ITrame> expected = new ArrayList<>(Collections.singletonList(trame1));

        List<ITrame> actual = fichierExtracteur.extraireTrames("test/file/data/trames_old.txt");

        System.out.println(expected);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
