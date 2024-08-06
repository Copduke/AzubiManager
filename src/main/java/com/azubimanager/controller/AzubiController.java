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
    public ResponseEntity<Azubi> addAzubi(@RequestBody Azubi azubi) {
        Azubi newAzubi = azubiService.addAzubi(azubi);
        return new ResponseEntity<>(newAzubi, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAzubi(@PathVariable("id") Long id) {
        azubiService.deleteAzubi(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
