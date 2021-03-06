package de.twins.equipment.domain;

import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Ortable;

public interface IsWeapon extends Ortable {

    double getStrength();

    void setOwner(AbstractFighter abstractFighter);
    AbstractFighter getOwner();

}
