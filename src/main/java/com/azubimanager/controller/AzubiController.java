package com.azubimanager.controller;

import com.azubimanager.model.Azubi;
import com.azubimanager.model.Department;
import com.azubimanager.model.JobTitle;
import com.azubimanager.repo.AzubiRepo;
import com.azubimanager.repo.DepartmentRepo;
import com.azubimanager.repo.JobTitleRepo;
import com.azubimanager.service.AzubiService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JobTitleRepo jobTitleRepo;
    @Autowired
    private AzubiRepo azubiRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    public AzubiController(AzubiService azubiService) {
        this.azubiService = azubiService;
    }

    @GetMapping
    public ModelAndView getAzubis() {
        List<Azubi> azubis = azubiService.findAllAzubis();
        List<JobTitle> jobTitles = azubiService.findAllJobTitles();
        List<Department> departments= azubiService.findAllDepartments();
        ModelAndView view = new ModelAndView("azubi");
        view.addObject("azubis", azubis);
        view.addObject("jobTitles", jobTitles);
        view.addObject("departments", departments);
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

    @GetMapping("/jobtitle/{id}")
    public ResponseEntity<JobTitle> getJobTitleById(@PathVariable("id") Long id) {
        JobTitle jobTitle = azubiService.findJobTitleById(id);
        return new ResponseEntity<>(jobTitle, HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
        Department department = azubiService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    /*@GetMapping("/titles")
    public ModelAndView getAzubiTitle() {
        List<JobTitle> jobTitles = azubiService.findAllJobTitles();
        ModelAndView view = new ModelAndView("titles");
        view.addObject("jobTitles", jobTitles);
        return view;
    }*/

    @PostMapping("/add")
    public String addAzubi(@ModelAttribute Azubi newAzubi, @RequestParam Long jobTitleId, @RequestParam Long departmentId) {
        JobTitle jobTitle = azubiService.findJobTitleById(jobTitleId);
        Department department = azubiService.findDepartmentById(departmentId);
        newAzubi.setJobTitle(jobTitle);
        //newAzubi.setDepartment(department);
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
        List<Department> departments = azubiService.findAllDepartments();
        model.addAttribute("azubi", azubi);
        model.addAttribute("jobTitles", jobTitles);
        model.addAttribute("departments", departments);
        return "update";
    }

    @Transactional
    @PostMapping("/update")
    public String updateAzubi(@ModelAttribute Azubi azubi, @RequestParam Long jobTitleId, @RequestParam Long departmentId) {
        JobTitle jobTitle = azubiService.findJobTitleById(jobTitleId);
        Department department = azubiService.findDepartmentById(departmentId);
        azubi.setJobTitle(jobTitle);
        // azubi.setDepartment(department);
        azubiService.saveAzubi(azubi);
        return "redirect:/azubi";
    }

    @GetMapping("/create")
    public ResponseEntity<String> create() {
        Azubi azubi = new Azubi();
        JobTitle jobTitle = new JobTitle();
        Department department = new Department();

        jobTitle.setTitle("Job1");
        department.setTitle("Department1");

        azubi.setName("bob");
        azubi.setEmail("bob@gmail.com");
        azubi.setPhone("123456789");

        azubiRepo.save(azubi);
        jobTitleRepo.save(jobTitle);
        departmentRepo.save(department);
        return new ResponseEntity<>("Create", HttpStatus.OK);
    }

    @GetMapping("/merge")
    public ResponseEntity<String> merge() {
        Azubi azubi = azubiService.findAzubiById(1L);
        JobTitle jobTitle = azubiService.findJobTitleById(1L);
        Department department = departmentRepo.findDepartmentById(1L);

        jobTitle.getAzubis().add(azubi);
        jobTitleRepo.save(jobTitle);
        department.getJobTitles().add(jobTitle);
        departmentRepo.save(department);

        return new ResponseEntity<>("Merge", HttpStatus.OK);
    }

    @GetMapping("/show/department")
    public ResponseEntity<Department> show() {
        return new ResponseEntity<>(departmentRepo.findDepartmentById(1L), HttpStatus.OK);
    }

    @GetMapping("/show/jobtitle")
    public ResponseEntity<JobTitle> showJob() {
        return new ResponseEntity<>(jobTitleRepo.findJobTitleById(1L), HttpStatus.OK);
    }

    @GetMapping("/show/azubi")
    public ResponseEntity<Azubi> showAzubi() {
        return new ResponseEntity<>(azubiRepo.findAzubiById(1L), HttpStatus.OK);
    }
}
