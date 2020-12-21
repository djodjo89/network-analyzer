package model.network.entete.icmp;

import model.network.entete.Entete;

import java.util.Arrays;
import java.util.List;

public class Icmp extends Entete {
    private final IdentifierBE identifierBE;
    private final IdentifierLE identifierLE;
    private final SequenceNumberBE sequenceNumberBE;
    private final SequenceNumberLE sequenceNumberLE;
    private final Data data;

    public Icmp(List<String> valeurs) {
        super("Internet Control Message Protocol",
                "",
                Arrays.asList(
                        new TypeIcmp(valeurs.get(0)),
                        new Code(valeurs.get(1)),
                        new Checksum(valeurs.get(2) + valeurs.get(3)),
                        new IdentifierBE(valeurs.get(4)),
                        new IdentifierLE(valeurs.get(5)),
                        new SequenceNumberBE(valeurs.get(6)),
                        new SequenceNumberLE(valeurs.get(7)),
                        new Data(valeurs.subList(8, valeurs.size()))
                ));
        identifierBE = new IdentifierBE(valeurs.get(4));
        identifierLE = new IdentifierLE(valeurs.get(5));
        sequenceNumberBE = new SequenceNumberBE(valeurs.get(6));
        sequenceNumberLE = new SequenceNumberLE(valeurs.get(7));
        data = new Data(valeurs.subList(8, valeurs.size()));
    }

    public IdentifierBE getIdentifierBE() {
        return identifierBE;
    }

    public IdentifierLE getIdentifierLE() {
        return identifierLE;
    }

    public SequenceNumberBE getSequenceNumberBE() {
        return sequenceNumberBE;
    }

    public SequenceNumberLE getSequenceNumberLE() {
        return sequenceNumberLE;
    }

    public Data getData() {
        return data;
    }
}
