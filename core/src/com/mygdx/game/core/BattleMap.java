package com.mygdx.game.core;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class BattleMap {
    private TextureRegion grassTexture;
    private TextureRegion appleTexture;
    private TextureRegion tomatoTextures;
    private TextureRegion nutTexture;
    private TextureRegion carrotTexture;
    int[][] artifact;
    float timePerFrame = 0.08f;
    float flameTimer = 0.0f;

    public BattleMap() {

        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");

        this.appleTexture = Assets.getInstance().getAtlas().findRegion("apple");
        this.tomatoTextures = Assets.getInstance().getAtlas().findRegion("tomato");
        this.nutTexture = Assets.getInstance().getAtlas().findRegion("nut");
        this.carrotTexture = Assets.getInstance().getAtlas().findRegion("carrot");

        artifact = new int[9][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                int temp = MathUtils.random(15);
                if (temp < 4) {
                    artifact[j][i] = temp + 1;
                } else artifact[j][i] = 0;
            }
        }
    }

    public void update(float dt) {
        flameTimer += dt;
    }


    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
                int arti = artifact[j][i];
                if (arti == 0 || arti > 5) continue;
                switch (arti) {
                    case 1:
                        batch.draw(appleTexture , i * 80 + 8, j * 80 + 8);
                        break;
                    case 2:
                        batch.draw(appleTexture , i * 80 + 8, j * 80 + 8);
                        break;
                    case 3:
                        batch.draw(nutTexture , i * 80 + 8, j * 80 + 8);
                        break;
                    case 4:
                        batch.draw(carrotTexture , i * 80 + 8, j * 80 + 8);
                        break;
                }
            }
        }
    }

    public int[][] getArtifact() {
        return artifact;
    }

}
