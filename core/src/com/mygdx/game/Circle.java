package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Circle {
    private final int RADIUS = 50;
    private Vector2 fieldSize;
    private Vector2 pos;
    Texture texture;

    Circle(int x, int y, Vector2 fieldSize) {
        this.pos = new Vector2(x, y);
        this.fieldSize = fieldSize;
        this.texture = new Texture("circle.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.x - RADIUS, pos.y - RADIUS);
    }

    public void update(Vector2 goalPos, Vector2 goalSize) {

        float deltaX = Math.abs(pos.x - goalPos.x);
        float deltaY = Math.abs(pos.y - goalPos.y);

        while (deltaX <= (RADIUS + goalSize.x / 2) && deltaY <= (RADIUS + goalSize.y / 2)) {
            pos.x = (float) (Math.random() * (fieldSize.x - RADIUS * 2f) + RADIUS);
            pos.y = (float) (Math.random() * (fieldSize.y - RADIUS * 2f) + RADIUS);
            deltaX = Math.abs(pos.x - goalPos.x);
            deltaY = Math.abs(pos.y - goalPos.y);
        }
    }

    public void dispose() {
        texture.dispose();
    }

}