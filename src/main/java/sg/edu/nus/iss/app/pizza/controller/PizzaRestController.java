package sg.edu.nus.iss.app.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.app.pizza.service.PizzaService;

@RestController
@RequestMapping(path = "/order", consumes ="application/json", produces = "application/json")
public class PizzaRestController {
    @Autowired
    PizzaService pizzaSvc;

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable(value="id") String id){
        System.out.println("TEST IN RESTCONTROLLER");
        String r = pizzaSvc.getById(id);
        System.out.println(r);

        JsonObject value = Json.createObjectBuilder()
        .add("message", "Order "+ id + " not found")
        .build();

        if(r== null){
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(value.toString());
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(r);

    }
}
