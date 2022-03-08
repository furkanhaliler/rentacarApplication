package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;
    
    @OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances;
    
    @OneToMany(mappedBy = "car")
    private List<Rent> rents;
    
    @Column(name= "rent_status")
    private Boolean rentStatus;

    @Column(name= "car_maintenance_status")
    private Boolean carMaintenanceStatus;
    
}
