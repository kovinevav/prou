package ru.kovynev.vahta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.rep.CompanyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FindService {
    final CompanyRepository companyRepository;

    public FindService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Iterable<Company> findCompanyByNameOrAnons(String name) {
        Iterable<Company> allFoundCompanies = companyRepository.findAllByNameContainingOrAnonsContaining(name, name);
        if(((ArrayList<Company>)allFoundCompanies).isEmpty()) allFoundCompanies = companyRepository.findAll();
        return allFoundCompanies;
    }
}

