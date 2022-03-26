package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer>{

	List<Payment> findByCustomerUserId(int customerUserId);
	
	Payment findByInvoiceInvoiceId(int invoiceId);
	
	List<Payment> findByRentRentId(int rentId);
}
