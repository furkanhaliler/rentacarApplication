package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {

	List<Car> findByDailyPriceLessThan(double requestedPrice);

	List<Car> findByDailyPriceGreaterThan(double requestedPrice);

	List<Car> findByDailyPriceBetween(double maxValue, double minValue);

	boolean existsByBrandId(int brandId);

	boolean existsByColorId(int colorId);

}
