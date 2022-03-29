package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "description")
    private String description;
    
    @Column(name = "kilometer")
    private Double kilometer;

    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;
    
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City baseCity;
    
    @Column(name = "rent_status")
    private boolean rentStatus;
    
    @Column(name = "maintenance_status")
    private boolean maintenanceStatus;
    
    @OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances;
    
    @OneToMany(mappedBy = "car")
    private List<Rent> rents;
    
    @OneToMany(mappedBy = "car")
    private List<Damage> damages;
   
}
