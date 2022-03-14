package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.CorporateCustomer;

@Repository
public interface CorporateCustomerDao extends UserDao<CorporateCustomer> {

	
}
