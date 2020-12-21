package model.network.champ.compose;

import model.network.champ.Champ;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChampCompose extends Champ<String> {
    public ChampCompose(String etiquette, List<String> valeurs, String concatenator, Function<String, String> mapper) {
        super(etiquette, valeurs
                .stream()
                .map(mapper::apply)
                .reduce((a, b) -> a + concatenator + b)
                .orElse("Incorrect adress"));
    }

    public ChampCompose(String etiquette, List<String> valeurs, String concatenator) {
        this(etiquette, valeurs, concatenator, val -> val);
    }

    public static List<String> extraireAdresse(int deb, int fin, List<String> valeursTrame) {
        return IntStream.range(deb, fin).mapToObj(valeursTrame::get).collect(Collectors.toList());
    }
}
