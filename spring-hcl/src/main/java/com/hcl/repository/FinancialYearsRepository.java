package com.hcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.entity.FinancialYears;


public interface FinancialYearsRepository /*extends JpaRepository<FinancialYears, Integer>*/{
	
		//@Query("select * from currentFY")
		public List<FinancialYears> findFinancialYears();

}
