package model.network.trame;

import model.network.entete.IEntete;

import java.util.List;

public interface ITrame {
    int getNo();
    double getTemps();
    String getSource();
    String getDestination();
    String getProtocoleAbreviation();
    String getInfo();
    List<IEntete> getEntetes();
    String getHexa();
}
