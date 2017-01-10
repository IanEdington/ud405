package com.udacity.gamedev.stickfigure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * In this exercise you'll set up a ShapeRenderer, and use it to draw a stick figure. At minimum,
 * you'll need a circle for the head, and five lines for the torso, arms, and legs.
 *
 * Remember to set up a ShapeRenderer you'll need to declare it, initialize it, and dispose of it.
 * Then to actually use the ShapeRenderer you'll need to start a batch of the appropriate type, draw
 * your shapes, and end the batch.
 *
 * We don't have step-by-step TODOs for this one, since the aim is for you to remember the steps for
 * setting up a ShapeRenderer and be able to set one up on your own. Of course, the solution is
 * available if you run into anything confusing.
 */
public class StickFigure extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        super.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float size = 50.0f;
        float width = size/25;
        Vector2 leftFoot = new Vector2(size, size);
        Vector2 rightFoot = new Vector2(3*size, size);
        Vector2 groin = new Vector2(2*size, 2*size);
        Vector2 sholders = new Vector2(2*size, 4*size);
        Vector2 head = new Vector2(2*size, 5*size);
        Vector2 leftHand = new Vector2(size, 4*size);
        Vector2 rightHand = new Vector2(3*size, 4*size);
        float headRadius = size/2;


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(head.x, head.y, headRadius);
        shapeRenderer.rectLine(leftFoot, groin, size/25.0f);
        shapeRenderer.rectLine(rightFoot, groin, size/25.0f);
        shapeRenderer.rectLine(groin, head, size/25.0f);
        shapeRenderer.rectLine(leftHand, rightHand, size/25.0f);

        shapeRenderer.end();
    }
}
