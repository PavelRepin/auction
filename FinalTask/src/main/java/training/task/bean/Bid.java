package training.task.bean;

import java.io.Serializable;

/**
 * The class stores objects with properties
 * <B> idLot </ b>, <b> idBidder </ b>.
 * It implements interface {@see Serializable}
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Bid implements Serializable {
    private int idLot;
    private int idBidder;

    public Bid() {

    }

    public Bid(int idLot, int idBidder) {
        this.idLot = idLot;
        this.idBidder = idBidder;
    }

    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
    }

    public int getIdBidder() {
        return idBidder;
    }

    public void setIdBidder(int idBidder) {
        this.idBidder = idBidder;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bid)) return false;

        Bid bid = (Bid) obj;

        if (idBidder != bid.idBidder) return false;
        if (idLot != bid.idLot) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLot;
        result = 31 * result + idBidder;
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "idLot=" + idLot +
                ", idBidder=" + idBidder +
                '}';
    }
}
