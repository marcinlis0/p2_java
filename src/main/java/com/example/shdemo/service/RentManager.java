package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Equipment;
import com.example.shdemo.domain.Type;

public interface RentManager {
	
	
	void AddType(Type type);
	Type GetTypeById(int id);
	List<Type> GetAllTypes();
	List<Type> GetTypeByName(String name);
	void DeleteAllTypes();
	void DeleteTypeByName(String name);
	boolean UpdateTypeById(int id, Type newType);
	
	
	void AddEquipment(Equipment equipment);
	Equipment GetEquipmentById(int id);
	List<Equipment> GetEquipmentByType(String type);
	List<Equipment> GetAllEquipments();
	void DeleteAllEquipments();
	void DeleteEquipmentByType(Type type);
	void UpdateEquipmentById(int id, Equipment newEquipment);
	void UpdateEquipmentByType(Type type, Equipment newEquipment);
	

}
