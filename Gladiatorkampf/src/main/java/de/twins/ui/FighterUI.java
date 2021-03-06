package de.twins.ui;


import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Gladiator;

import java.awt.*;

/**
 * UI reprensentation of a fighter.
 *
 * @author Pierre
 */
public class FighterUI extends AbstractFighterUI {


    public FighterUI(PlayerType id, AbstractFighter fighter, int x, int y) {
        super(id, fighter, x, y);
    }

    public FighterUI(PlayerType id, Gladiator gladiator) {
        super(id, gladiator, gladiator.getX(), gladiator.getY());
    }

    @Override
    public void render(Graphics g) {
        if (getPlayerType() == PlayerType.PLAYER) {
            g.setColor(Color.WHITE);
        }

        //draw body
        g.fillRect(getX(), getY(), width, height);
        //arms
        g.fillRect(getX() + width, getY(), width / 5, height / 5);
        g.fillRect(getX() - 2, getY(), width / 5, height / 5);
        //legs
        g.fillRect(getX(), getY() + height, width / 5, height / 2);
        g.fillRect(getX() + width - 2, getY() + height, width / 5, height / 2);
        //head
        g.fillRect(getX() + (width / 4), getY() - 5, width / 2, height / 2);
    }

}
