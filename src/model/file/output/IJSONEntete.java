package model.file.output;

import model.network.entete.IEntete;

import java.util.List;
import java.util.stream.Collectors;

public class IJSONEntete {
    private IEntete entete;

    public IJSONEntete(IEntete entete) {
        this.entete = entete;
    }

    public String getProtocole() {
        return entete.getProtocole();
    }

    public String getProtocoleAbreviation() {
        return entete.getProtocoleAbreviation();
    }

    public String getDescription() {
        return entete.getDescription();
    }

    public List<IJSONChamp> getChamps() {
        return entete.getChamps().stream().map(IJSONChamp::new).collect(Collectors.toList());
    }
}
