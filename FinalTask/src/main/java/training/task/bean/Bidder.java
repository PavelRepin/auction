package training.task.bean;

import java.io.Serializable;

/**
 * The class stores objects with properties
 * <B> id </ b>, <b> name </ b>, <b> lastName </ b>, <b> money </ b>, <b> age </ b>, <b> documentNumber </ b>, <b> country </ b>,
 * <b> fullAddress </ b>, <b> login </ b>, <b> password </ b>, <b> email </ b>, <b> local </ b>, <b> isAdmin </ b>, <b> isBlocked </ b>.
 * It implements interface {@see Serializable}
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Bidder implements Serializable {
    private int id;
    private String name;
    private String lastName;
    private double money;
    private int age;
    private String documentNumber;
    private String country;
    private String fullAddress;
    private String login;
    private String password;
    private String email;
    private String local;
    private boolean isAdmin;
    private boolean isBlocked;

    public Bidder() {
    }

    public Bidder(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Bidder(String login, String password, String email, int age, String local) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
        this.local = local;
    }

    public Bidder(int id, String name, String lastName, double money, int age, String documentNumber, String country, String fullAddress, String login, String password, String email, String local, boolean isAdmin, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.money = money;
        this.age = age;
        this.documentNumber = documentNumber;
        this.country = country;
        this.fullAddress = fullAddress;
        this.login = login;
        this.password = password;
        this.email = email;
        this.local = local;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bidder)) return false;

        Bidder bidder = (Bidder) o;

        if (age != bidder.age) return false;
        if (id != bidder.id) return false;
        if (isAdmin != bidder.isAdmin) return false;
        if (isBlocked != bidder.isBlocked) return false;
        if (Double.compare(bidder.money, money) != 0) return false;
        if (country != null ? !country.equals(bidder.country) : bidder.country != null) return false;
        if (documentNumber != null ? !documentNumber.equals(bidder.documentNumber) : bidder.documentNumber != null)
            return false;
        if (email != null ? !email.equals(bidder.email) : bidder.email != null) return false;
        if (fullAddress != null ? !fullAddress.equals(bidder.fullAddress) : bidder.fullAddress != null) return false;
        if (lastName != null ? !lastName.equals(bidder.lastName) : bidder.lastName != null) return false;
        if (local != null ? !local.equals(bidder.local) : bidder.local != null) return false;
        if (login != null ? !login.equals(bidder.login) : bidder.login != null) return false;
        if (name != null ? !name.equals(bidder.name) : bidder.name != null) return false;
        if (password != null ? !password.equals(bidder.password) : bidder.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + age;
        result = 31 * result + (documentNumber != null ? documentNumber.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (fullAddress != null ? fullAddress.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bidder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", money=" + money +
                ", age=" + age +
                ", documentNumber='" + documentNumber + '\'' +
                ", country='" + country + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", local='" + local + '\'' +
                ", isAdmin=" + isAdmin +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
