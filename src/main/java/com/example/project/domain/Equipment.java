package com.example.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "equipment.all", query = "Select e from Equipment e"),
    @NamedQuery(name = "findByType", query = "from Equipment e where e.type.name=:type"),
})
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String model;
	private double price;
	
	@ManyToOne()
	private Type type;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
