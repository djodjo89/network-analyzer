package model.file.output;

import model.network.entete.IEntete;
import model.network.trame.ITrame;

import java.util.List;
import java.util.stream.Collectors;

public class IJSONTrame {
    private ITrame trame;
    public IJSONTrame(ITrame trame) {
        this.trame = trame;
    }

    int getNo() {
        return trame.getNo();
    }

    String getSource() {
        return trame.getSource();
    }

    String getDestination() {
        return trame.getDestination();
    }

    String getProtocoleAbreviation() {
        return trame.getProtocoleAbreviation();
    }

    String getInfo() {
        return trame.getInfo();
    }

    public List<IJSONEntete> getEntetes() {
        return trame.getEntetes().stream().map(IJSONEntete::new).collect(Collectors.toList());
    }

    String getHexa() {
        return trame.getHexa();
    }

    int getLength() {
        return trame.getLength();
    }
}
