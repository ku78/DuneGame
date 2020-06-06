package com.dune.game.core;

import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.UnitType;

import java.util.List;

public class AiLogic {
    private GameController gc;
    private List<AbstractUnit> units;
    private List<AbstractUnit> enemies;
    private BattleMap map;
    private Vector2 tmp;
    private float tmpPos;


    public AiLogic (GameController gc){
        this.gc = gc;
        map = gc.getMap();
    }

    public void update (float dt){
        units = gc.getUnitsController().getAiUnits();
        enemies = gc.getUnitsController().getPlayerUnits();
        tmp = new Vector2();
        for (int i = 0; i < units.size(); i++) {
            if(units.get(i).getUnitType() == UnitType.HARVESTER){
                commandHarvestResource(units.get(i));
            } else if (units.get(i).getUnitType() == UnitType.BATTLE_TANK){
                commandAttackTarget(units.get(i));
            }
        }
    }

    public void commandAttackTarget (AbstractUnit unit){
        tmpPos = unit.getPosition().dst(enemies.get(0).getPosition());
        for (int i = 0; i < enemies.size(); i++) {
            if(unit.getPosition().dst(enemies.get(i).getPosition()) < tmpPos ){
                tmpPos  = unit.getPosition().dst(enemies.get(i).getPosition());
                unit.commandAttack(enemies.get(i));
            }
        }
    }


    public void commandHarvestResource (AbstractUnit unit){
        unit.commandMoveTo(findNearestResource(unit.getPosition()));
    }

    public Vector2 findNearestResource (Vector2 unitPosition){
        tmpPos  = unitPosition.dst(enemies.get(0).getPosition());;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if((map.getResourceCount(i, j) > 0) && (unitPosition.dst(i*80, j*80) < tmpPos )){
                    tmpPos  = unitPosition.dst(i*80 + 40, j*80 + 40);
                    tmp.set(i*80 + 40, j*80 + 40);
                }
            }
        }

        if (tmpPos  !=1000){return tmp;}
        else {return unitPosition;}
    }
}