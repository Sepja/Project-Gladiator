package de.twins.gladiator.process;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import de.twins.gladiator.domain.Equipment.Rarity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SimpleEquipFactoryImplComponentTest.Config.class })
@TestPropertySource("equipment_test.properties")
public class SimpleEquipFactoryImplComponentTest {
	static class Config {
		@Bean
		public SimpleEquipFactoryImpl factoryImpl() {
			return new SimpleEquipFactoryImpl();
		}
	}

	@Autowired
	SimpleEquipFactoryImpl testee;

	@Test
	public void shouldLoadPropertiesForEquipment() {
		String[] baseProperties = { "healthpoints", "defense", "attack" };
		
		HashMap<Rarity,Integer> baseValues = new HashMap<>();
		baseValues.put(Rarity.COMMON, 20);
		baseValues.put(Rarity.UNCOMMON, 50);
		baseValues.put(Rarity.MAGIC, 100);
		baseValues.put(Rarity.EPIC, 200);
		baseValues.put(Rarity.LEGENDARY, 500);
		
		//gives every baseProperty a distinct value to test that all properties are drawn
		HashMap<String,Integer> propertyValue = new HashMap<>();
		propertyValue.put(baseProperties[0],0);
		propertyValue.put(baseProperties[1],1);
		propertyValue.put(baseProperties[2],2);
		
		for (Rarity rarity : Rarity.values()) {
			for (String baseproperty : baseProperties) {
				int property = testee.getProperty(baseproperty,rarity);
				assertThat(property, is(baseValues.get(rarity)+propertyValue.get(baseproperty)));
			}
		}


	}
}