package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.Customer;

@Repository
public interface CustomerDao extends UserDao<Customer>{

}
