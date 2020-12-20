package model.network.trame;

import model.network.INetworkObject;
import model.network.NetworkObject;
import model.network.entete.IEntete;

import java.util.List;
import java.util.Objects;

public class Trame extends ITrame {
    private final int no;
    private final double temps;
    private final String source;
    private final String destination;
    private final String protocoleAbreviation;
    private final String info;
    private final List<IEntete> entetes;
    private final String hexa;
    private final int length;

    public Trame(int no, double temps, String source, String destination, String protocoleAbreviation, String info, List<IEntete> entetes, String hexa, int length) {
        this.no = no;
        this.temps = temps;
        this.source = source;
        this.destination = destination;
        this.protocoleAbreviation = protocoleAbreviation;
        this.info = info;
        this.entetes = entetes;
        this.hexa = hexa;
        this.length = length;
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

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trame trame = (Trame) o;
        boolean entetesEqual = true;
        int i = 0;

        while (entetesEqual && i < getEntetes().size()) {
            entetesEqual = getEntetes().get(i).equals(((Trame) o).getEntetes());
            i++;
        }
        return getNo() == trame.getNo() && Double.compare(trame.getTemps(), getTemps()) == 0 && getSource().equals(trame.getSource()) && getDestination().equals(trame.getDestination()) && getProtocoleAbreviation().equals(trame.getProtocoleAbreviation()) && getInfo().equals(trame.getInfo()) &&
                entetesEqual && getHexa().equals(trame.getHexa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNo(), getTemps(), getSource(), getDestination(), getProtocoleAbreviation(), getInfo(), getEntetes(), getHexa());
    }

    @Override
    public String toString() {
        return "Trame{" +
                "no=" + no +
                ", temps=" + temps +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", protocoleAbreviation='" + protocoleAbreviation + '\'' +
                ", info='" + info + '\'' +
                //", entetes=" + entetes.stream().map(entete -> '\t' + entete.toString()).reduce("", (e1, e2) -> e1 + '\n' + e2) +
                ", hexa='" + hexa + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public List<? extends INetworkObject> getChildren() {
        return entetes;
    }

    @Override
    public String getValue() {
        return String.valueOf(no);
    }

    @Override
    public boolean isRoot() {
        return true;
    }
}
