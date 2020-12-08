package model.network.trame;

import model.network.entete.IEntete;

import java.util.List;

public class Trame implements ITrame {
    private final int no;
    private final double temps;
    private final String source;
    private final String destination;
    private final String protocoleAbreviation;
    private final String info;
    private final List<IEntete> entetes;
    private final String hexa;

    public Trame(int no, double temps, String source, String destination, String protocoleAbreviation, String info, List<IEntete> entetes, String hexa) {
        this.no = no;
        this.temps = temps;
        this.source = source;
        this.destination = destination;
        this.protocoleAbreviation = protocoleAbreviation;
        this.info = info;
        this.entetes = entetes;
        this.hexa = hexa;
    }

    @Override
    public int getNo() {
        return no;
    }

    @Override
    public double getTemps() {
        return temps;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public String getProtocoleAbreviation() {
        return protocoleAbreviation;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public List<IEntete> getEntetes() {
        return entetes;
    }

    @Override
    public String getHexa() {
        return hexa;
    }
}
