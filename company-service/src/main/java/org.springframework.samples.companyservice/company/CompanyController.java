package org.springframework.samples.companyservice.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
  private final CompanyService companyService;

  @GetMapping
  public List<CompanyDto> findCompanies() {
    return companyService.findCompanies();
  }

  @PostMapping
  public Company addCompany(@RequestBody Company company) {
    return companyService.addCompany(company);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CompanyDto> findCompany(@PathVariable("id") long id) {
    CompanyDto company = companyService.findCompany(id);
    if (company == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(company, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable("id") long id) {
    boolean deleted = companyService.deleteCompany(id);
    if (deleted) {
      return new ResponseEntity<>("This company Deleted",HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCompany(@PathVariable("id") long id, @RequestBody Company updatedCompany) {
    boolean updated = companyService.updateCompany(id, updatedCompany);
    if (updated) {
      return new ResponseEntity<>("This company Updated",HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
