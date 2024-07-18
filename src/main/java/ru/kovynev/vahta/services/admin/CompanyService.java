package ru.kovynev.vahta.services.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.rep.CompanyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    public List<String> getNamesAllCompanies() {
      List<String> namesOfCompanies = List.of();
     namesOfCompanies = companyRepository.findAll().stream()
              .map(Company::getName)
              .toList();
     return namesOfCompanies;
    }



}
