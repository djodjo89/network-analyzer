package model.network;

import java.util.List;

public interface Trame {
    int getNo();
    double getTime();
    String getSource();
    String getDestination();
    String getProtocoleAbreviation();
    String getInfo();
    List<Entete> getEntetes();
    String getHexa();
}
