package sg.edu.nus.iss.app.pizza.model;

import java.util.Random;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contact {

    @NotNull(message="Please enter your name")
    @Size(min=3, max=999, message="Name must be more than 3 char")
    private String name;
    @NotNull(message="Please enter your address")
    @Size(min=1, max=999, message="Please enter your address")
    private String address;
    @Pattern(regexp = "^\\d{8}$", message="Phone Number must be at least 8 digit")
    private String phone;
    private boolean rush;
    private String comment;
    private String id;
    private Float total;
    private Float totalRush;

    public Contact(){
        this.id=generateId(8);
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getTotalRush() {
        return totalRush;
    }

    public void setTotalRush(Float totalRush) {
        this.totalRush = totalRush;
    }



    

}
