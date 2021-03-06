package com.codeclan.example.pirateservice_d1_starter;

import com.codeclan.example.pirateservice_d1_starter.models.Pirate;
import com.codeclan.example.pirateservice_d1_starter.models.Raid;
import com.codeclan.example.pirateservice_d1_starter.models.Ship;
import com.codeclan.example.pirateservice_d1_starter.repositories.PirateRepository;
import com.codeclan.example.pirateservice_d1_starter.repositories.RaidRepository;
import com.codeclan.example.pirateservice_d1_starter.repositories.ShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PirateserviceD1StarterApplicationTests {

	@Autowired
	PirateRepository pirateRepository;

	@Autowired
	ShipRepository shipRepository;

	@Autowired
	RaidRepository raidRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindPiratesOver30(){
		List<Pirate> foundPirates = pirateRepository.findPiratesByAgeGreaterThan(30);
		assertTrue(foundPirates.size() > 0);
	}

	@Test
	public void canFindRaidByLocation(){
		List<Raid> foundRaids = raidRepository.findRaidsByLocation("Havana");
		assertTrue(foundRaids.size() > 0);
		assertEquals("Havana",foundRaids.get(0).getLocation());
	}

	@Test
	public void canCountRaidByLocation(){
		List<Raid> countRaids = raidRepository.countRaidsByLocation("Havana");
		assertEquals("[1]",countRaids.toString());
	}

	@Test
	public void canFindPiratesByRaidId(){
		List<Pirate> foundPirates = pirateRepository.findPiratesByRaidsId(1L);
		assertTrue(foundPirates.size() > 0);
		assertEquals("Jack", foundPirates.get(0).getFirstName());
	}

	@Test
	public void canFindShipByPiratesByFirstName(){
		List<Ship> foundShips = shipRepository.findShipsByPiratesFirstName("Edward");
		assertEquals("Queen Anne's Revenge", foundShips.get(0).getName());
	}

	@Test
	public void canFindRaidsByShipId(){
		List<Raid> foundRaids = raidRepository.findRaidsByPiratesShipId(1L);
		assertTrue(foundRaids.size() > 0);
	}

	@Test
	public void canFindRaidsByPiratesShip(){
		Ship foundShip = shipRepository.getOne(2L);
		List<Raid> foundRaids = raidRepository.findByPiratesShip(foundShip);
		assertTrue(foundRaids.size() > 0);
	}

//	Database Seeding (not advised to do in a Test file)
//	@Test
//	public void createPirateAndShip(){
//		Ship ship = new Ship("The Flying Dutchman");
//		shipRepository.save(ship);
//
//		Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
//		pirateRepository.save(pirate1);
//	}
//
//	@Test
//	public void addPiratesAndRaids(){
//		Ship ship = new Ship("The Flying Dutchman");
//		shipRepository.save(ship);
//
//		Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
//		pirateRepository.save(pirate1);
//
//		Raid raid1 = new Raid("Tortuga", 100);
//		raidRepository.save(raid1);
//
//		raid1.addPirate(pirate1);
//		raidRepository.save(raid1);
//
//	}

}
