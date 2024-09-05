package com.jiit.vikram.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiit.vikram.dto.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Company findByCompanyName(String companyName);
}

