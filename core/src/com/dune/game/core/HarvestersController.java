package com.dune.game.core;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.BattleTank;
import com.dune.game.core.units.Harvester;
import com.dune.game.core.units.Owner;

public class HarvestersController extends ObjectPool<Harvester> {
    private GameController gc;

    @Override
    protected Harvester newObject() {
        return new Harvester(gc);
    }

    public HarvestersController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void setup(float x, float y, Owner ownerType) {
        Harvester t = activateObject();
        t.setup(ownerType, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
            dischargeResource(activeList.get(i));
        }
        checkPool();
    }

    public void dischargeResource(Harvester unit) {
        if (Math.abs(unit.getPosition().x - gc.getMap().getPositionDischarge().x) < 37
                && Math.abs(unit.getPosition().y - gc.getMap().getPositionDischarge().y) < 17
                && unit.getContainer() > 0) {
            gc.getPlayerLogic().setMoney(unit.getContainer() + gc.getPlayerLogic().getMoney());
            unit.setContainer(0);

        }
    }
}