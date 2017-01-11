package com.udacity.gamedev.screensaver;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

/**
 *
 *
 * This class represents a ball, bouncing around the screen. It maintains a position and velocity, and it needs to knows how to update its position, based on how much time has passed.
 *
 * and has basic physics for colliding with the "walls" (the edges of the screen).
 */



public class BouncingBall {

    private static final Color COLOR = Color.RED;
    private static final float RADIUS_FACTOR = 1.0f / 60;
    private static final float KICK_VELOCITY = 500.0f;

    float radius;
    Vector2 position;
    Vector2 velocity;

    public BouncingBall(Viewport viewport) {
        init(viewport);
    }

    public void init(Viewport viewport) {
        // give a new ball a random position
        Random random = new Random();
        radius = RADIUS_FACTOR * Math.min(viewport.getWorldWidth(), viewport.getWorldHeight());
        float posX = random.nextFloat() * (viewport.getWorldWidth() - 2 * radius) + radius;
        float posY = random.nextFloat() * (viewport.getWorldHeight() - 2 * radius) + radius;
        position = new Vector2(posX, posY);

        velocity = new Vector2();
        randomKick();
    }

    private void randomKick() {
        Random random = new Random();
        float angle = MathUtils.PI2 * random.nextFloat();
        velocity.x = KICK_VELOCITY * MathUtils.cos(angle);
        velocity.y = KICK_VELOCITY * MathUtils.sin(angle);
    }

    public void update(float delta, Viewport viewport) {

        // Update the ball's position using its velocity
        position.x += delta * velocity.x;
        position.y += delta * velocity.y;


        collideWithWalls(radius, viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    private void collideWithWalls(float radius, float viewportWidth, float viewportHeight) {
        if (position.x - radius < 0) {
            position.x = radius;
            velocity.x = -velocity.x;
        }
        if (position.x + radius > viewportWidth) {
            position.x = viewportWidth - radius;
            velocity.x = -velocity.x;
        }

        // Make the ball bounce off the bottom of the screen
        if (position.y - radius < 0) {
            position.y = radius;
            velocity.y = -velocity.y;
        }

        // Make the ball bounce off the top of the screen
        if (position.y + radius > viewportHeight) {
            position.y = viewportHeight - radius;
            velocity.y = -velocity.y;
        }

    }

    public void render(ShapeRenderer renderer) {
        renderer.set(ShapeType.Filled);
        renderer.setColor(COLOR);
        renderer.circle(position.x, position.y, radius);
    }
}

