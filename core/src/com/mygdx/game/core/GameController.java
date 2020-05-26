package com.mygdx.game.core;

import com.badlogic.gdx.math.MathUtils;


public class GameController {
    private BattleMap map;
    private ProjectilesController projectilesController;
    private Tank tank;

    public Tank getTank() {
        return tank;
    }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public BattleMap getMap() {
        return map;
    }

    public GameController() {
        Assets.getInstance().loadAssets();
        this.map = new BattleMap();
        this.projectilesController = new ProjectilesController(this);
        this.tank = new Tank(this, 200, 200);
    }

    public void update(float dt) {
        map.update(dt);
        tank.update(dt);
        projectilesController.update(dt);
        checkCollisions(dt);
    }

    public void checkCollisions(float dt) {
        int tankTileRow = MathUtils.floor(tank.getPosition().y / 80);
        int tankTileCol = MathUtils.floor(tank.getPosition().x / 80);
        for (int row = tankTileRow - 1; row <= tankTileRow + 1; row++) {
            if (row < 0 || row > 8) continue;
            for (int col = tankTileCol - 1; col <= tankTileCol + 1; col++) {
                if (col < 0 || col > 15) continue;
                float tileCenterY = row * 80 + 40;
                float tileCenterX = col * 80 + 40;
                if (tank.getPosition().dst(tileCenterX, tileCenterY) < 50) {
                    map.getArtifact()[row][col] = 0;
                }

            }
        }

    }
}