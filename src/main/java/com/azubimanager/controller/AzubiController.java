package com.azubimanager.controller;

import com.azubimanager.model.Azubi;
import com.azubimanager.model.JobTitle;
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
    public ModelAndView getAzubis() {
        List<Azubi> azubis = azubiService.findAllAzubis();
        List<JobTitle> jobTitles = azubiService.findAllJobTitles();
        ModelAndView view = new ModelAndView("azubi");
        view.addObject("azubis", azubis);
        view.addObject("jobTitles", jobTitles);
        // view.addObject("newAzubi", new Azubi());
        return view;
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
    public String addAzubi(@ModelAttribute Azubi newAzubi, @RequestParam Long jobTitleId) {
        JobTitle jobTitle = azubiService.findJobTitleById(jobTitleId);
        newAzubi.setJobTitle(jobTitle);
        azubiService.saveAzubi(newAzubi);
        jobTitle.getAzubis().add(newAzubi);
        return "redirect:/azubi";
    }

    @PostMapping("/delete/{id}")
    public String deleteAzubi(@PathVariable Long id) {
        azubiService.deleteAzubiById(id);
        return "redirect:/azubi";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Azubi azubi = azubiService.findAzubiById(id);
        List<JobTitle> jobTitles = azubiService.findAllJobTitles();
        model.addAttribute("azubi", azubi);
        model.addAttribute("jobTitles", jobTitles);
        return "update";
    }

    @PostMapping("/update")
    public String updateAzubi(@ModelAttribute Azubi azubi, @RequestParam Long jobTitleId) {
        JobTitle jobTitle = azubiService.findJobTitleById(jobTitleId);
        azubi.setJobTitle(jobTitle);
        azubiService.saveAzubi(azubi);
        return "redirect:/azubi";
    }
}
