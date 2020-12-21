package model.file.output;

import model.network.trame.ITrame;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FichierSauvegardeur implements IFichierSauvegardeur {
    @Override
    public void sauvegarder(String path, List<ITrame> inputTrames) {
        ObjectMapper mapper = new ObjectMapper();

        class JSONER {
            private final List<ITrame> trames = inputTrames;

            public List<IJSONTrame> getTrames() {
                return trames
                        .stream()
                        .map(IJSONTrame::new)
                        .collect(Collectors.toList());
            }
        }

        try {
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new JSONER());

            Files.writeString(Paths.get(path), json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
