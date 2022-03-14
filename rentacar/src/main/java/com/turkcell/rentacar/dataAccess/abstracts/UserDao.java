package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entities.concretes.User;

@Repository
public interface UserDao<T extends User> extends JpaRepository<T, Integer> {

}
