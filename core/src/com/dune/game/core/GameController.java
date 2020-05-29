package com.dune.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class GameController {
    private BattleMap map;
    private ProjectilesController projectilesController;
    private TanksController tanksController;
    private Vector2 CO;

    public TanksController getTanksController() {
        return tanksController;
    }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public BattleMap getMap() {
        return map;
    }

    // Инициализация игровой логики
    public GameController() {
        Assets.getInstance().loadAssets();
        this.map = new BattleMap();
        this.projectilesController = new ProjectilesController(this);
        this.tanksController = new TanksController(this);
        this.tanksController.setup(200, 200, Tank.Owner.PLAYER);
        this.tanksController.setup(400, 400, Tank.Owner.PLAYER);
        CO = new Vector2();
    }

    public void update(float dt) {
        switchSelected();
        tanksController.update(dt);
        projectilesController.update(dt);
        map.update(dt);
        checkCollisions(dt);
    }

    public void checkCollisions(float dt) {
        for (int i = tanksController.activeList.size()-1; i >= 0; i--) {
            for (int j = tanksController.activeList.size()-1; j >= 0; j--) {
                if(i != j){
                    if(checkCoordinates(tanksController.activeList.get(i).getPosition(), tanksController.activeList.get(j).getPosition(),80)){
                        tanksController.activeList.get(j).blockMove();
                    }
                }
            }
        }
    }

    public void switchSelected(){
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            CO.set(Gdx.input.getX(), 720 - Gdx.input.getY());
            for (int i = tanksController.activeList.size()-1; i >= 0; i--) {
                if(checkCoordinates(CO,tanksController.activeList.get(i).getPosition(),35)){
                    tanksController.activeList.get(i).setSelected();
                }else{
                    tanksController.activeList.get(i). setNotSelected();
                }
            }
        }
    }

    protected boolean checkCoordinates (Vector2 obj1, Vector2 obj2, int distance){
        return(Math.abs(obj1.x-obj2.x) < distance && Math.abs(obj1.y-obj2.y) < distance);
    }
}
