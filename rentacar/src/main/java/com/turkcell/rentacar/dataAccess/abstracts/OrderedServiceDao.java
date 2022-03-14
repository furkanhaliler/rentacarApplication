package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.OrderedService;

@Repository
public interface OrderedServiceDao extends JpaRepository<OrderedService, Integer>{

	List<OrderedService> findOrderedServicesByRent_RentId(int rentId);
}
