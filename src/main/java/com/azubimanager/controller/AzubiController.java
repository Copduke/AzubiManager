package com.azubimanager.controller;

import com.azubimanager.model.Azubi;
import com.azubimanager.service.AzubiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/azubi")
public class AzubiController {

    private final AzubiService azubiService;

    public AzubiController(AzubiService azubiService) {
        this.azubiService = azubiService;
    }

    @GetMapping
    public String getAzubis(Model model) {
        List<Azubi> azubis = azubiService.findAllAzubis();
        model.addAttribute("azubis", azubis);
        model.addAttribute("newAzubi", new Azubi());
        return "azubi";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Azubi>> getAllAzubis() {
        List<Azubi> azubis = azubiService.findAllAzubis();
        return new ResponseEntity<>(azubis, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Azubi> getAzubiById(@PathVariable("id") Long id) {
        Azubi azubi = azubiService.findAzubiById(id);
        return new ResponseEntity<>(azubi, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addAzubi(@ModelAttribute Azubi newAzubi, Model model) {
        azubiService.saveAzubi(newAzubi);
        return "redirect:/azubi";
    }

    //@PostMapping("/add")
    /*public ResponseEntity<Azubi> addAzubi(@RequestBody Azubi azubi) {
        Azubi newAzubi = azubiService.addAzubi(azubi);
        return new ResponseEntity<>(newAzubi, HttpStatus.CREATED);
    }*/

    @PostMapping("/delete/{id}")
    public String deleteAzubi(@PathVariable Long id) {
        azubiService.deleteAzubiById(id);
        return "redirect:/azubi";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Azubi azubi = azubiService.findAzubiById(id);
        model.addAttribute("azubi", azubi);
        return "update";
    }

    @PostMapping("/update")
    public String updateAzubi(@ModelAttribute Azubi azubi) {
        azubiService.saveAzubi(azubi);
        return "redirect:/azubi";
    }
}
