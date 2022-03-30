package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entity.Contact;


import java.util.Map;
import java.util.TreeMap;

@Controller
public class HomeController {
    Map<String, Contact> contacts;

    public HomeController() {
        this.contacts = new TreeMap<>();
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("contacts", this.contacts);
        return modelAndView;
    }

    @PostMapping("/")
    public String add(Contact contact) {
        this.contacts.put(contact.getName(), contact);
        return "redirect:/";
    }

    @GetMapping("/edit{name}")
    public ModelAndView editForm(@PathVariable String name, ModelAndView modelAndView) {
        modelAndView.setViewName("edit");
        modelAndView.addObject("contact", contacts.get(name));
        return modelAndView;
    }

    @PutMapping("/edit{name}")
    public String edit(@PathVariable String name, Contact contact) {
        Contact contact1 = contacts.remove(name);
        contact1.setName(contact.getName());
        contact1.setNumber(contact.getNumber());
        contacts.put(contact.getName(), contact1);
        return "redirect:/";
    }

    @DeleteMapping("/delete{name}")
    public String delete(@PathVariable String name) {
        this.contacts.remove(name);
        return "redirect:/";
    }


}
