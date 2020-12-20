package file;

import model.file.output.FichierSauvegardeur;
import model.network.champ.Champ;
import model.network.champ.IChamp;
import model.network.entete.Entete;
import model.network.entete.IEntete;
import model.network.trame.ITrame;
import model.network.trame.Trame;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FichierSauvegardeurTest {
    @Test
    public void testSauvegarde() {
        FichierSauvegardeur fichierSauvegardeur = new FichierSauvegardeur();

        Champ destination = new Champ("Destination", "52:54:00:12:35:02");
        Champ source = new Champ("Source", "08:00:27:48:75:d2");
        Champ type = new Champ("Type", "0x0800");
        List<IChamp> champs = new ArrayList<>(Arrays.asList(destination, source, type));

        Entete ethernet = new Entete("Ethernet II", "Src: PcsCompu_48:75:d2 (08:00:27:48:75:d2), Dst: RealtekU_12:35:02 (52:54:00:12:35:02)", champs);
        List<IEntete> entetes = new ArrayList<>(Collections.singletonList(ethernet));

        ITrame trameEthernet = new Trame(
                1,
                1.02569,
                "192.168.0.1",
                "192.168.0.2",
                "TCP",
                "443 → 37756 [ACK] Seq=1 Ack=518 Win=65535 Len=0",
                entetes,
                "08 00 27 48 75 d2 52 54 00 12 35 02 08 00 45 00 00 28 fd 63 00 00 40 06 ad f6 ac d9 16 8e 0a 00 02 0f 01 bb 93 7c 13 ca 68 02 ab bd dc e6 50 10 ff ff 46 b6 00 00 00 00 00 00 00 00",
                62
        );
        List<ITrame> trames = new ArrayList<>(Collections.singletonList(trameEthernet));

        fichierSauvegardeur.sauvegarder(trames);

         try {
             String expected = Files.readString(Paths.get("test/file/data/trames.json"));
             String actual = Files.readString(Paths.get("test/file/data/trames_test.json"));
            assertEquals(expected, actual);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
