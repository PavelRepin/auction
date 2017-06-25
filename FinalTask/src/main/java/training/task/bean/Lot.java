package training.task.bean;

import java.io.Serializable;

/**
 * The class stores objects with properties
 * <B> id </ b>, <b> name </ b>, <b> category </ b>, <b> author </ b>, <b> year </ b>, <b> description </ b>, <b> startPrice </ b>,
 * <b> image </ b>, <b> ownerBidder </ b>, <b> sellerBidder </ b>, <b> createdAt </ b>, <b> currentPrice </ b>, <b> lastBidder </ b>, <b> closeAt </ b>.
 * It implements interface {@see Serializable}
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Lot implements Serializable {
    private int id;
    private String name;
    private String category;
    private String author;
    private int year;
    private String description;
    private double startPrice;
    private String image;
    private int ownerBidder;
    private int sellerBidder;
    private long createdAt;
    private double currentPrice;
    private int lastBidder;
    private long closeAt;

    public Lot() {
    }

    public Lot(int id, String name, String category, String author, int year, String description, double startPrice, String image, int ownerBidder, int sellerBidder, long createdAt, double currentPrice, int lastBidder, long closeAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.year = year;
        this.description = description;
        this.startPrice = startPrice;
        this.image = image;
        this.ownerBidder = ownerBidder;
        this.sellerBidder = sellerBidder;
        this.createdAt = createdAt;
        this.currentPrice = currentPrice;
        this.lastBidder = lastBidder;
        this.closeAt = closeAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOwnerBidder() {
        return ownerBidder;
    }

    public void setOwnerBidder(int ownerBidder) {
        this.ownerBidder = ownerBidder;
    }

    public int getSellerBidder() {
        return sellerBidder;
    }

    public void setSellerBidder(int sellerBidder) {
        this.sellerBidder = sellerBidder;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getLastBidder() {
        return lastBidder;
    }

    public void setLastBidder(int lastBidder) {
        this.lastBidder = lastBidder;
    }

    public long getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(long closeAt) {
        this.closeAt = closeAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot)) return false;

        Lot lot = (Lot) o;

        if (closeAt != lot.closeAt) return false;
        if (createdAt != lot.createdAt) return false;
        if (Double.compare(lot.currentPrice, currentPrice) != 0) return false;
        if (id != lot.id) return false;
        if (lastBidder != lot.lastBidder) return false;
        if (ownerBidder != lot.ownerBidder) return false;
        if (sellerBidder != lot.sellerBidder) return false;
        if (Double.compare(lot.startPrice, startPrice) != 0) return false;
        if (year != lot.year) return false;
        if (author != null ? !author.equals(lot.author) : lot.author != null) return false;
        if (category != null ? !category.equals(lot.category) : lot.category != null) return false;
        if (description != null ? !description.equals(lot.description) : lot.description != null) return false;
        if (image != null ? !image.equals(lot.image) : lot.image != null) return false;
        if (name != null ? !name.equals(lot.name) : lot.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(startPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + ownerBidder;
        result = 31 * result + sellerBidder;
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        temp = Double.doubleToLongBits(currentPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + lastBidder;
        result = 31 * result + (int) (closeAt ^ (closeAt >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", image='" + image + '\'' +
                ", ownerBidder=" + ownerBidder +
                ", sellerBidder=" + sellerBidder +
                ", createdAt=" + createdAt +
                ", currentPrice=" + currentPrice +
                ", lastBidder=" + lastBidder +
                ", closeAt=" + closeAt +
                '}';
    }
}