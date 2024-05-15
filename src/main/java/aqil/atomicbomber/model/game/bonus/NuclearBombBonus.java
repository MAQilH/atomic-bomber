package aqil.atomicbomber.model.game.bonus;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.obstacles.Obstacle;

public class NuclearBombBonus extends Bonus{
    public NuclearBombBonus(Game game, Obstacle org) {
        super(game, org, "nuclear-bomb.png");
    }

    @Override
    public void gain() {
        game.setNumberOfNuclearBombs(game.getNumberOfNuclearBombs() + 1);
    }
}
