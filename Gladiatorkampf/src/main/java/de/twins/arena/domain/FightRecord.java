package de.twins.arena.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import de.twins.arena.domain.ArenaResult.Result;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Persistable;

@Entity
public class FightRecord extends Persistable {

	@ManyToOne
	private AbstractFighter fighter;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AbstractFighter> enemies;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AbstractFighter> allies;

	private BigDecimal dmgTaken;

	private BigDecimal dmgInflicted;

	@Enumerated(EnumType.STRING)
	private Result result;

	public FightRecord(AbstractFighter fighter) {
		this.fighter = fighter;
		dmgTaken = BigDecimal.ZERO;
		dmgInflicted = BigDecimal.ZERO;
	}

	public void addDmgInflicted(BigDecimal damageDoneToFighter) {
		dmgInflicted = this.dmgInflicted.add(damageDoneToFighter);

	}

	public void addDmgTaken(BigDecimal damageDoneToFighter) {
		dmgTaken = this.dmgTaken.add(damageDoneToFighter);
	}

	public List<AbstractFighter> getAllies() {
		return allies;
	}

	public BigDecimal getDmgInflicted() {
		return dmgInflicted;
	}

	public BigDecimal getDmgTaken() {
		return dmgTaken;
	}

	public List<AbstractFighter> getEnemies() {
		return enemies;
	}

	public AbstractFighter getFighter() {
		return fighter;
	}

	public Result getResult() {
		return result;
	}

	public void setAllies(List<AbstractFighter> allies) {
		this.allies = allies;
	}

	public void setDmgInflicted(BigDecimal dmgDone) {
		this.dmgInflicted = dmgDone;
	}

	public void setDmgTaken(BigDecimal dmgTaken) {
		this.dmgTaken = dmgTaken;
	}

	public void setEnemies(List<AbstractFighter> enemies) {
		this.enemies = enemies;
	}

	public void setFigher(AbstractFighter fightable) {
		this.fighter = fightable;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
