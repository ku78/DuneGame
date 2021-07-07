package com.dune.game.core.units;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.Assets;
import com.dune.game.core.GameController;
import com.dune.game.core.Targetable;
import com.dune.game.core.Weapon;

public class Harvester extends AbstractUnit {
    public Harvester(GameController gc) {
        super(gc);
        this.textures = Assets.getInstance().getAtlas().findRegion("tankcore").split(64, 64)[0];
        this.weaponTexture = Assets.getInstance().getAtlas().findRegion("harvester");
        this.containerCapacity = 10;
        this.minDstToActiveTarget = 5.0f;
        this.speed = 120.0f;
        this.weapon = new Weapon(4.0f, 1);
        this.hpMax = 500;
        this.unitType = UnitType.HARVESTER;
    }

    @Override
    public void setup(Owner ownerType, float x, float y) {
        this.position.set(x, y);
        this.ownerType = ownerType;
        this.hp = this.hpMax;
        this.destination = new Vector2(position);
    }

    public void updateWeapon(float dt) {
        if (gc.getMap().getResourceCount(position) > 0 && container < containerCapacity) {
            int result = weapon.use(dt);
            float particlePositionX;
            float particlePositionY;
            Vector2 point = new Vector2();
            for (int i = 0; i < 15; i++) {
                particlePositionX = position.x + 25 + MathUtils.random(0, 15);
                particlePositionY = position.y + MathUtils.random(-10, 10);
                gc.getParticleController().setup(
                        particlePositionX,
                        particlePositionY,
                        position.x - particlePositionX, position.y - particlePositionY,
                        1f, 0.5f, 0.2f,
                        0f, 0.4f, 0.7f, 0.7f,
                        0f, 0.6f, 0.5f, 0.5f);
            }
            for (int i = 0; i < 40; i++) {
                while (!(Math.abs(point.dst(position)) < 50 && Math.abs(point.dst(position)) > 35)) {
                    point.x = position.x + MathUtils.random(-50, 50);
                    point.y = position.y + MathUtils.random(-50, 50);
                }
                gc.getParticleController().setup(point.x, point.y,
                        MathUtils.random(-15, 15), MathUtils.random(15, 15), 0.1f, 0.5f, 0.3f,
                        0f, 0.4f, 0.7f, 0.7f,
                        0f, 0.6f, 0.5f, 0.5f);
                point.set(-100, - 100);
            }
            if (result > -1) {
                container += gc.getMap().harvestResource(position, result);
            }
        } else {
            weapon.reset();
        }
    }

    @Override
    public void commandAttack(Targetable target) {
        commandMoveTo(target.getPosition());
    }

    @Override
    public void renderGui(SpriteBatch batch) {
        super.renderGui(batch);
        if (weapon.getUsageTimePercentage() > 0.0f) {
            batch.setColor(0.2f, 0.2f, 0.0f, 1.0f);
            batch.draw(progressbarTexture, position.x - 32, position.y + 22, 64, 8);
            batch.setColor(1.0f, 1.0f, 0.0f, 1.0f);
            batch.draw(progressbarTexture, position.x - 30, position.y + 24, 60 * weapon.getUsageTimePercentage(), 4);
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}