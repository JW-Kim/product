package product.auth.model;

public class AuthGetParam {
    public String telNumber;

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "AuthGetParam{" +
                "telNumber=" + telNumber +
                '}';
    }
}
