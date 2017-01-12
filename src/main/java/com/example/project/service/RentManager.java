package com.example.project.service;

import java.util.List;

import com.example.project.domain.Equipment;
import com.example.project.domain.Type;

public interface RentManager {
	
	
	void AddType(Type type);
	Type GetTypeById(long id);
	List<Type> GetAllTypes();
	Type GetTypeByName(String name);
	void DeleteType(Type type);
	boolean UpdateType(Type type);
	
	
	void AddEquipment(Equipment equipment);
	Equipment GetEquipmentById(long id);
	List<Equipment> GetEquipmentByType(String type);
	List<Equipment> GetAllEquipments();
	void DeleteEquipmentByType(Type type);
	boolean UpdateEquipment(Equipment equipment);

}
