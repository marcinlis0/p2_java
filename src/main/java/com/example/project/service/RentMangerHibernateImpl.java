package com.example.project.service;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.domain.Equipment;
import com.example.project.domain.Type;

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
	public Type GetTypeById(long id) {
		return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> GetAllTypes() {
		return sessionFactory.getCurrentSession().getNamedQuery("type.all").list();
	}

	@Override
	public Type GetTypeByName(String name) {
		return (Type) sessionFactory.getCurrentSession().getNamedQuery("findByName").setString("name", name)
				.uniqueResult();
	}

	@Override
	public void DeleteType(Type type) {
		Type typeToDelete = (Type) sessionFactory.getCurrentSession().get(Type.class, type.getId());

		List<Equipment> equipments = GetAllEquipments();

		for (Equipment e : equipments) {
			if (typeToDelete == e.getType()) {
				e.setType(null);
				sessionFactory.getCurrentSession().update(e);
			}
		}
		sessionFactory.getCurrentSession().delete(typeToDelete);
	}

	@Override
	public boolean UpdateType(Type type) {
		try {
			sessionFactory.getCurrentSession().update(type);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public void AddEquipment(Equipment equipment) {
		sessionFactory.getCurrentSession().persist(equipment);

	}

	@Override
	public Equipment GetEquipmentById(long id) {
		return (Equipment) sessionFactory.getCurrentSession().get(Equipment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> GetEquipmentByType(String type) {
		return sessionFactory.getCurrentSession().getNamedQuery("findByType").setString("type", type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> GetAllEquipments() {
		return sessionFactory.getCurrentSession().getNamedQuery("equipment.all").list();
	}

	@Override
	public void DeleteEquipmentByType(Type type) {
		List<Equipment> equipments = GetAllEquipments();

		for (Equipment e : equipments) {
			if (e.getType() == type) {
				Equipment equipmentToDelete = (Equipment) sessionFactory.getCurrentSession().get(Equipment.class, e.getId());
				sessionFactory.getCurrentSession().delete(equipmentToDelete);
			}
		}
	}

	@Override
	public boolean UpdateEquipment(Equipment equipment) {
		try {
			sessionFactory.getCurrentSession().update(equipment);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}
