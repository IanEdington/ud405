package com.udacity.gamedev.sierpinskitriangle;

import java.lang.Math;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Your challenge, should you choose to accept it, is to draw a Serpinski Triangle. I offer no hints
 * beyond the fact that ShapeRenderer has a very convenient triangle() function, and that using a
 * FitViewport can simplify matters considerably. Good luck!
 */


public class SierpinskiTriangle extends ApplicationAdapter {

    static final float SIZE = 10;
    private static final int RECURSIONS = 7;
    private static final float WORLD_WIDTH = 100;
    private static final float WORLD_HEIGHT = (WORLD_WIDTH/2)*(float)Math.sqrt(3);

    private ShapeRenderer renderer;
    private FitViewport viewport;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        // starting points of triangle
        Vector2 A = new Vector2(0,0);
        Vector2 B = new Vector2(WORLD_WIDTH, 0);
        Vector2 C = new Vector2(WORLD_WIDTH/2, WORLD_HEIGHT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.triangle(A.x, A.y, B.x, B.y, C.x, C.y);
        renderer.setColor(Color.WHITE);
        punchTriangle(A, B, C, RECURSIONS);
        renderer.end();
    }

    public void punchTriangle(Vector2 A, Vector2 B, Vector2 C, int recursions) {
        if (0 == recursions) return;
        Vector2 newA = new Vector2((A.x+B.x)/2, (A.y+B.y)/2);
        Vector2 newB = new Vector2((C.x+B.x)/2, (C.y+B.y)/2);
        Vector2 newC = new Vector2((C.x+A.x)/2, (C.y+A.y)/2);
        renderer.triangle(newA.x, newA.y, newB.x, newB.y, newC.x, newC.y);

        recursions--;
        punchTriangle(A, newA, newC, recursions);
        punchTriangle(B, newA, newB, recursions);
        punchTriangle(C, newB, newC, recursions);
    }
}
