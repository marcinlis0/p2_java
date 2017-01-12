package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Equipment;
import com.example.shdemo.domain.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class RentManagerTest {

	@Autowired
	RentManager rentManager;

	private final static String NAME = "Narty";
	private final static String PURPOSE = "Wąski stok";
	private final static String MODEL = "Voelkl Stroke";
	private final static double PRICE = 25.50;

	private final static String NEWNAME = "Snowboard";
	private final static String NEWPURPOSE = "Szeroki stok";
	private final static String NEWMODEL = "Voelkl Dash";
	private final static double NEWPRICE = 30.00;
	
	private final static String THIRDNAME = "Sanki";

	@Before
	public void add() {
		if (rentManager.GetTypeByName(NAME) == null) 
		{
			Type type = new Type();
			type.setName(NAME);
			type.setPurpose(PURPOSE);
			rentManager.AddType(type);

			Equipment equipment = new Equipment();
			equipment.setModel(MODEL);
			equipment.setPrice(PRICE);
			equipment.setType(type);
			rentManager.AddEquipment(equipment);
		} 
		else if (rentManager.GetEquipmentByType(NAME) == null) 
		{
			Equipment equipment = new Equipment();
			equipment.setModel(MODEL);
			equipment.setPrice(PRICE);
			equipment.setType(rentManager.GetTypeByName(NAME));
			rentManager.AddEquipment(equipment);
		}

	}

	@Test
	public void addTypeCheck() {
		Type retrievedType = (Type) rentManager.GetTypeByName(NAME);

		assertEquals(NAME, retrievedType.getName());
		assertEquals(PURPOSE, retrievedType.getPurpose());
	}

	@Test
	public void getTypeByIdCheck() {
		int retrievedId = (int) rentManager.GetTypeByName(NAME).getId();
		Type retrievedType = rentManager.GetTypeById(retrievedId);

		assertEquals(retrievedId, retrievedType.getId());
	}

	@Test
	public void GetAllTypes() {
		int startSize = rentManager.GetAllTypes().size();
		Type type = new Type();
		type.setName(NEWNAME);
		type.setPurpose(NEWPURPOSE);
		rentManager.AddType(type);

		List<Type> retrievedTypes = rentManager.GetAllTypes();

		assertEquals(1, retrievedTypes.size() - startSize);
	}

	@Test
	public void GetTypeByNameCheck() {
		String retrievedName = (String) rentManager.GetAllTypes().get(0).getName();
		Type retrievedType = (Type) rentManager.GetTypeByName(retrievedName);

		assertEquals(retrievedName, retrievedType.getName());
	}

	@Test
	public void DeleteTypeCheck() {
		int startSize = rentManager.GetAllTypes().size();
		Type retrievedType = rentManager.GetTypeByName(NAME);
		rentManager.DeleteType(retrievedType);
		assertEquals(null, rentManager.GetTypeByName(NAME));
		assertEquals(startSize - 1, rentManager.GetAllTypes().size());
	}

	@Test
	public void UpdateTypeCheck() {
		Type retrievedType = rentManager.GetTypeByName(NAME);
		retrievedType.setName(THIRDNAME);
		assertEquals(true, rentManager.UpdateType(retrievedType));
		assertEquals(null, rentManager.GetTypeByName(NAME));
		assertEquals(Type.class, rentManager.GetTypeByName(THIRDNAME).getClass());
	}

	@Test
	public void AddEquipmentCheck() {
		Equipment retrievedEquipment = (Equipment) rentManager.GetAllEquipments().get(0);

		assertEquals(MODEL, retrievedEquipment.getModel());
		assertEquals(PRICE, retrievedEquipment.getPrice(), 0.01);
	}

	@Test
	public void GetEquipmentByIdCheck() {
		int retrievedId = (int) rentManager.GetAllEquipments().get(0).getId();
		Equipment retrievedEquipment = (Equipment) rentManager.GetEquipmentById(retrievedId);

		assertEquals(retrievedId, retrievedEquipment.getId());
	}

	@Test
	public void GetEquipmentByTypeCheck() {
		Type retrievedType = rentManager.GetTypeByName(NAME);
		List<Equipment> retrievedEquipments = rentManager.GetEquipmentByType(NAME);
		assertEquals(retrievedType, retrievedEquipments.get(0).getType());
	}

	@Test
	public void GetAllEquipmentsCheck() {
		int startSize = rentManager.GetAllTypes().size();
		
		if(rentManager.GetTypeByName(NEWNAME) == null){
			Type type = new Type();
			type.setName(NEWNAME);
			type.setPurpose(NEWPURPOSE);

			rentManager.AddType(type);
			
			Equipment equipment = new Equipment();
			equipment.setModel(NEWMODEL);
			equipment.setPrice(NEWPRICE);
			equipment.setType(type);
			
			rentManager.AddEquipment(equipment);
			
			startSize++;
		}
		else if(rentManager.GetEquipmentByType(NEWNAME) == null){
			Equipment equipment = new Equipment();
			equipment.setModel(NEWMODEL);
			equipment.setPrice(NEWPRICE);
			equipment.setType(rentManager.GetTypeByName(NEWNAME));
			
			rentManager.AddEquipment(equipment);
			startSize++;
		}
		
		List<Equipment> retrievedEquipments = rentManager.GetAllEquipments();

		assertEquals(startSize, retrievedEquipments.size());
	}

	@Test
	public void UpdateEquipmentCheck() {
		if(rentManager.GetTypeByName(NEWNAME) == null){
			Type type = new Type();
			type.setName(NEWNAME);
			type.setPurpose(NEWPURPOSE);
			rentManager.AddType(type);
		}
		

		Type oldType = rentManager.GetTypeByName(NAME);

		Equipment retrievedEquipment = rentManager.GetAllEquipments().get(0);
		retrievedEquipment.setType(rentManager.GetTypeByName(NEWNAME));
		assertEquals(true, rentManager.UpdateEquipment(retrievedEquipment));

		assertEquals(0, rentManager.GetEquipmentByType(oldType.getName()).size());
		assertEquals(1, rentManager.GetEquipmentByType(NEWNAME).size());
	}

	@Test
	public void DeleteEquipmentByTypeCheck() {
		if(rentManager.GetTypeByName(NEWNAME) == null){
			Type type = new Type();
			type.setName(NEWNAME);
			type.setPurpose(NEWPURPOSE);
			rentManager.AddType(type);
			
			Equipment equipment = new Equipment();
			equipment.setModel(NEWMODEL);
			equipment.setPrice(NEWPRICE);
			equipment.setType(type);
			rentManager.AddEquipment(equipment);
		}
		else if(rentManager.GetEquipmentByType(NEWNAME) == null){
			Equipment equipment = new Equipment();
			equipment.setModel(NEWMODEL);
			equipment.setPrice(NEWPRICE);
			equipment.setType(rentManager.GetTypeByName(NEWNAME));
			rentManager.AddEquipment(equipment);
		}

		int startSize = rentManager.GetAllEquipments().size();

		assertEquals(1, rentManager.GetEquipmentByType(rentManager.GetTypeByName(NEWNAME).getName()).size());
		rentManager.DeleteEquipmentByType(rentManager.GetTypeByName(NEWNAME));
		assertEquals(0, rentManager.GetEquipmentByType(rentManager.GetTypeByName(NEWNAME).getName()).size());
		assertEquals(startSize - 1, rentManager.GetAllEquipments().size());
	}

}
