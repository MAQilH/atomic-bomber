package aqil.atomicbomber.model.game.bonus;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.obstacles.Obstacle;

public class ClusterBombBonus extends Bonus{
    public ClusterBombBonus(Game game, Obstacle org) {
        super(game, org, "cluster-bomb.png");
    }

    @Override
    public void gain() {
        game.setNumberOfClusterBombs(game.getNumberOfClusterBombs() + 1);
    }
}
