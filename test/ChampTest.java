import model.network.champ.Champ;
import model.network.champ.ListeChamps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChampTest {
    @Test
    public void testChampSimple() {
        Champ champ = new Champ("Time to live", 128);

        assertEquals("Time to live: 128", champ.getEtiquette() + ": " + champ.getValeur());
    }

    @Test
    public void testListeChamps() {
        ListeChamps options = new ListeChamps("Option");
        ListeChamps ipOptionRecordRoute = new ListeChamps("IP Option - Record Route");
        Champ type = new Champ("Type", 7);
        Champ length = new Champ("Length", 39);
        Champ pointer = new Champ("Pointer", 4);
        options.addChamp(ipOptionRecordRoute);
        ipOptionRecordRoute.addChamp(type);
        ipOptionRecordRoute.addChamp(length);
        ipOptionRecordRoute.addChamp(pointer);

    }
}
