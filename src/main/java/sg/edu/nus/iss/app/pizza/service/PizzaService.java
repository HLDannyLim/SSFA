package sg.edu.nus.iss.app.pizza.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.app.pizza.model.Contact;
import sg.edu.nus.iss.app.pizza.model.Pizza;

@Service
public class PizzaService {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    
    private Float rush ;
    private Float pizzaPrice ;
    private Float pizzaSize;
    private Float total;
    

    public Float calPrice(Contact c, Pizza p){
        if(p.getPizza().equals("margherita")){
            pizzaPrice = 22f;
        }
        if(p.getPizza().equals("trioFormaggio")){
            pizzaPrice = 25f;
        }
        if(p.getPizza().equals("bella")||p.getPizza().equals("marinara")||p.getPizza().equals("spianataCalabrese")){
            pizzaPrice = 30f;
        }
        if(p.getSize().equals("sm")){
            pizzaSize = 1f;
        }
        if(p.getSize().equals("md")){
            pizzaSize = 1.2f;
        }
        if(p.getSize().equals("lg")){
            pizzaSize = 1.5f;
        }

        System.out.println(pizzaPrice);
        System.out.println(pizzaSize);
        System.out.println(Integer.parseInt(p.getQuantity()));

        
        total = (float) (pizzaPrice*pizzaSize*Integer.parseInt(p.getQuantity()));

        return total;
    }

    public Float calPriceWithRush(Float total,Contact c){

        if(c.isRush()==true){
            rush = 2f;
        }else{
            rush= 0f;
        }

        System.out.println(rush);
        
        total = (float) total+rush;

        return total;
    }

    public JsonObject generateJsonData(Contact c, Pizza p,Float total){
        
        JsonObject value = Json.createObjectBuilder()
        .add("orderId", c.getId())
        .add("name", c.getName())
        .add("address", c.getAddress())
        .add("phone", c.getPhone())
        .add("rush", c.isRush())
        .add("comments", c.getComment())
        .add("pizza", p.getPizza())
        .add("size", p.getSize())
        .add("quantity", p.getQuantity())
        .add("total", total)
        .build();

        return value ;
    }

    public void saveToRedis(Contact c,JsonObject o){

        // // if send to string us this methos
        // System.out.println("getbody to string"+resp.getBody().toString());
        // System.out.println("get body"+resp.getBody());
        // System.out.println("resp"+resp);
        // redisTemplate.opsForValue().set(c.getId(),resp.getBody().toString());

        redisTemplate.opsForValue().set(c.getId(), o.toString());
        String result = (String) redisTemplate.opsForValue().get(c.getId());
        System.out.println("  RESULT >>> " + result);
    }
    
    public String getById(String Id){
        // System.out.println("ID == "+Id);
        // System.out.println(redisTemplate.opsForValue().get(Id).toString());
        String r = (String) redisTemplate.opsForValue().get(Id);
        System.out.println(r);

        return r ;
        // return (CovidResult) redisTemplate.opsForValue().get(Id);
    }


}
