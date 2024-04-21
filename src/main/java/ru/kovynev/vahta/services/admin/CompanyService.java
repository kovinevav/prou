package ru.kovynev.vahta.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.rep.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    final CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<String> getNamesAllCompanies() {
      List<String> namesOfCompanies = List.of();
     namesOfCompanies = companyRepository.findAll().stream()
              .map(x->x.getName())
              .toList();
     return namesOfCompanies;
    }



}
