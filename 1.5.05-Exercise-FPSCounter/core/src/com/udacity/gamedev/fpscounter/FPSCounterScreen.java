package com.udacity.gamedev.fpscounter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FPSCounterScreen extends ScreenAdapter{

    // Declare a SpriteBatch, a BitmapFont, and a ScreenViewport
    SpriteBatch sprite;
    BitmapFont font;
    ScreenViewport viewport;


    @Override
    public void show() {

        // Initialize the SpriteBatch
        sprite = new SpriteBatch();


        // Initialize the BitmapFont
        font = new BitmapFont();




        // Initialize the ScreenViewport
        viewport = new ScreenViewport();

    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport. Be sure to center the camera
        viewport.update(width, height, true);

    }

    @Override
    public void dispose() {
        // Dispose of the SpriteBatch and the BitmapFont
        sprite.dispose();
        font.dispose();


    }

    @Override
    public void render(float delta) {
        // Apply the viewport
        viewport.apply();


        // Set the clear color
        Gdx.gl.glClearColor(0, 0, 0, 1);


        // Clear the color buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Set the SpriteBatch's projection matrix
        sprite.setProjectionMatrix(viewport.getCamera().combined);


        // Begin a new batch
        sprite.begin();
        sprite.setColor(Color.YELLOW);


        // Use delta to figure out the number of frames per second
        Float fps = 1 / delta;


        // Use the BitmapFont to draw the FPS
        font.draw(sprite, fps.toString(), viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);


        // End the batch
        sprite.end();

    }
}
