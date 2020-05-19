package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class DuneGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private SpriteBatch batch2;
	private Tank tank;
	private Circle circle;
	@Override
	public void create() {
		Vector2 fieldSize = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		batch = new SpriteBatch();
		tank = new Tank(200, 200);

		batch2 = new SpriteBatch();
		circle = new Circle(700, 400, fieldSize);
	}

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0.4f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		tank.render(batch);
		circle.render(batch);
		batch.end();
	}

	public void update(float dt) {
		circle.update(tank.getPosition(), tank.getSize());
		tank.update(dt);
	}

	@Override
	public void dispose() {
		batch.dispose();
		tank.dispose();
		circle.dispose();
	}

	// Домашнее задание:
	// - Задать координаты точки, и нарисовать в ней круг (любой круг, радиусом пикселей 50)
	// - Если "танк" подъедет вплотную к кругу, то круг должен переместиться в случайную точку
	// - * Нельзя давать танку заезжать за экран
}