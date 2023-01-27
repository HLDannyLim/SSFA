package sg.edu.nus.iss.app.pizza.model;


import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Pizza {

    @NotNull(message="please choose one pizza")
    private String pizza;
    @NotNull(message="please choose one size")
    private String size;
    
    // @Min(value=1 , message="Please select a Minimun of 1 pizza and a Maximum of 10 pizza")
    // @Max(value=10, message="Please select a Minimun of 1 pizza and a Maximum of 10 pizza")
    // @Range(min = 1, max = 10, message="Numbers only between 10 and 50")
    // private Integer quantity;

    // @Pattern(regexp = "^\\d{7}$", message="Phone Number must be at least 7 digit")
    // @NotNull(message="Name cannot be null")
    // @Size(min=3, max=64, message="Name must be between 3 and 64 chars")
    @Range(min = 1, max = 10, message="Numbers only between 1 and 10")
    private String quantity;



    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }



    



}
