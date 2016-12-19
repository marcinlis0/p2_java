package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Equipment;
import com.example.shdemo.domain.Type;

@Component
@Transactional
public class RentMangerHibernateImpl implements RentManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void AddType(Type type) {
		sessionFactory.getCurrentSession().persist(type);
		
	}

	@Override
	public Type GetTypeById(int id) {
		return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> GetAllTypes() {
		return sessionFactory.getCurrentSession().getNamedQuery("type.all").list();
	}

	@Override
	public List<Type> GetTypeByName(String name) {
		return sessionFactory.getCurrentSession().getNamedQuery("findByName")
				.setString("name", name).list();
	}

	@Override
	public void DeleteAllTypes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteTypeByName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean UpdateTypeById(int id, Type newType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void AddEquipment(Equipment equipment) {
		sessionFactory.getCurrentSession().persist(equipment);
		
	}

	@Override
	public Equipment GetEquipmentById(int id) {
		return (Equipment) sessionFactory.getCurrentSession().get(Equipment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> GetEquipmentByType(String type) {
		return sessionFactory.getCurrentSession().getNamedQuery("findByType")
				.setString("type", type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> GetAllEquipments() {
		return sessionFactory.getCurrentSession().getNamedQuery("equipment.all").list();
	}

	@Override
	public void DeleteAllEquipments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteEquipmentByType(Type type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateEquipmentById(int id, Equipment newEquipment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateEquipmentByType(Type type, Equipment newEquipment) {
		// TODO Auto-generated method stub
		
	}

	

}
