package de.twins.gladiator.process;

import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource(value = "classpath:config/simple_equipment.properties")
public class SimpleEquipFactory implements EquipmentFactory {
    private static final String PREFIX = "simpleequipment.";
    @Autowired
    private Environment env;

    @Override
    public Equipment createRandomEquipment() {
        return createRandomEquipmentFor(getRandomBodyPart());
    }

    @Override
    public Equipment createRandomEquipmentFor(BodyPart bodypart) {
        return createRandomEquipmentWithRarityGrade(bodypart, getRandomRarity());
    }

    @Override
    public Equipment createRandomEquipmentWithRarityGrade(BodyPart bodypart, Rarity rarity) {
        BigDecimal hp = randomHp(rarity);
        BigDecimal attack = randomDefense(rarity);
        BigDecimal defense = randomAttack(rarity);
        return new Equipment(bodypart, hp, attack, defense, rarity);
    }

    /*
     * (non-javadoc)
     * returns if property can be found the value will be returned else 0
     */
    protected int getProperty(String baseKey, Rarity rarity) {
        return Integer.parseInt(env.getProperty(PREFIX + baseKey + "." + rarity.toString().toLowerCase(), "0"));
    }

    private BodyPart getRandomBodyPart() {
        BodyPart[] bodyParts = BodyPart.values();
        return bodyParts[(int) (Math.random() * bodyParts.length)];
    }

    private Rarity getRandomRarity() {
        Rarity[] rarities = Rarity.values();
        return rarities[(int) (Math.random() * rarities.length)];
    }

    private BigDecimal randomAttack(Rarity rarity) {
        int attack = getProperty("doAttackMove", rarity);
        return new BigDecimal(randomizeValue(attack));
    }

    private BigDecimal randomDefense(Rarity rarity) {
        int defense = getProperty("defense", rarity);
        return new BigDecimal(randomizeValue(defense));
    }

    @Override
    public Map<BodyPart, Equipment> randomFullSet() {
        Map<BodyPart, Equipment> equipmentSet = new HashMap<>();
        for (BodyPart bodyPart : BodyPart.values()) {
            Equipment randomPart = createRandomEquipmentFor(bodyPart);
            equipmentSet.put(bodyPart, randomPart);
        }
        return equipmentSet;
    }

    private BigDecimal randomHp(Rarity rarity) {
        int maxHp = getProperty("healthpoints", rarity);
        return new BigDecimal(randomizeValue(maxHp));
    }

    private double randomizeValue(int maxHp) {
        return Math.random() * maxHp + 1;
    }
}
