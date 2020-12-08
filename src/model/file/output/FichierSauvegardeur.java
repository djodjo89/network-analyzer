package model.file.output;

import model.network.trame.ITrame;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FichierSauvegardeur implements IFichierSauvegardeur {
    @Override
    public void sauvegarder(List<ITrame> inputTrames) {
        ObjectMapper mapper = new ObjectMapper();

        class JSONER {
            private final List<ITrame> trames = inputTrames;

            public List<ITrame> getTrames() {
                return trames;
            }
        }

        try {
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new JSONER());

            Files.writeString(Paths.get("test/file/data/trames_test.json"), json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
