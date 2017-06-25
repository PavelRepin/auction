package training.task.bean;

import java.io.Serializable;

/**
 * The class stores objects with properties
 * <B> id </ b>, <b> price </ b>, <b> soldAt </ b>, <b> idLot </ b>, <b> idBidder </ b>.
 * It implements interface {@see Serializable}
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Sale implements Serializable {
    private int id;
    private double price;
    private long soldAt;
    private int idLot;
    private int idBidder;

    public Sale() {
    }

    public Sale(int id, double price, long soldAt, int idLot, int idBidder) {
        this.id = id;
        this.price = price;
        this.soldAt = soldAt;
        this.idLot = idLot;
        this.idBidder = idBidder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(long soldAt) {
        this.soldAt = soldAt;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;

        Sale sale = (Sale) o;

        if (id != sale.id) return false;
        if (idBidder != sale.idBidder) return false;
        if (idLot != sale.idLot) return false;
        if (Double.compare(sale.price, price) != 0) return false;
        if (soldAt != sale.soldAt) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (soldAt ^ (soldAt >>> 32));
        result = 31 * result + idLot;
        result = 31 * result + idBidder;
        return result;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", price=" + price +
                ", soldAt=" + soldAt +
                ", idLot=" + idLot +
                ", idBidder=" + idBidder +
                '}';
    }
}
