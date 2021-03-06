package com.turkcell.rentacar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.Invoice;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

	List<Invoice> findByCustomerUserId(int customerUserId);
	
	List<Invoice> findByRentRentId(int rentId);
	
	List<Invoice> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate);
	
	boolean existsByRentRentId(int rentId);
}
