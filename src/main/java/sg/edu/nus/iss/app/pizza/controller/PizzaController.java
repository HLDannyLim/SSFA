package sg.edu.nus.iss.app.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.pizza.model.Contact;
import sg.edu.nus.iss.app.pizza.model.Pizza;
import sg.edu.nus.iss.app.pizza.service.PizzaService;

@Controller
@RequestMapping(path = "/")
public class PizzaController {

    @Autowired
    PizzaService pizzaSvc;

    Pizza p ;

    @GetMapping
    public String getPizzaForm(Model model){
        System.out.println("inside pizza getmapping");
        model.addAttribute("pizza", new Pizza());
        return "view0";
    }

    @PostMapping
    public String postPizza(@Valid Pizza pizza, 
                BindingResult bResult, Model model){
        System.out.println("inside pizza postmapping");
        System.out.println(model.toString());
        System.out.println(bResult);
        System.out.println(pizza.getQuantity());
        System.out.println(pizza.getPizza());
        System.out.println(pizza.getSize());
        if(bResult.hasErrors()){
            return "view0";
        }
        this.p=pizza ;
        return "redirect:/contact";
    }

    @GetMapping("/contact")
    public String getContactForm(Model model){
        System.out.println("inside contact getmapping");
        model.addAttribute("contact", new Contact());
        return "view1";
    }

    @PostMapping("/contact")
    public String postContact(@Valid Contact contact, 
    BindingResult bResult, Model model){
        System.out.println("inside contact postmapping");
        System.out.println(model.toString());
        System.out.println(bResult);
        System.out.println(contact.getName());

        if(bResult.hasErrors()){
        return "view1";
        }

        Float total = pizzaSvc.calPrice(contact, p);
        Float totalWithRush = pizzaSvc.calPriceWithRush(total, contact);
        contact.setTotal(total);
        contact.setTotalRush(totalWithRush);
        System.out.println(total);
        System.out.println(totalWithRush);
        JsonObject o = pizzaSvc.generateJsonData(contact, p, total);
        pizzaSvc.saveToRedis(contact, o);
        return "view2";
    }


}
