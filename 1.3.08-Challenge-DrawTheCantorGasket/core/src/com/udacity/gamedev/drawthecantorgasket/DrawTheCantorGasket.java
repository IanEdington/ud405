package com.udacity.gamedev.drawthecantorgasket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/*

The Cantor gasket is a fractal where we start with a white square. We divide that square up into a 3x3 grid of smaller squares, then remove the middle square. Finally, we repeat the process on each of the remaining 8 squares.

 */

public class DrawTheCantorGasket extends ApplicationAdapter {

    ShapeRenderer shapeRenderer;
    // Set a constant for how many recursions to draw. 5 is a good place to start
    int numRecursion = 5;

    @Override
    public void create () {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Finds a good place to draw our fractal
        // Rectangle has members x,y for the lower left corner, and width and height
        Rectangle bounds = findLargestSquare();

        // Begin a filled shapeRenderer batch
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw a white square matching the bounds
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Set the working color to black, and call punchCantorGasket with the bounds
        shapeRenderer.setColor(Color.BLACK);
        punchCantorGasket(bounds.x, bounds.y, bounds.width, numRecursion);

        // End the batch
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        shapeRenderer = new ShapeRenderer();
    }


    private void punchCantorGasket(float x, float y, float size, int recursions){
        // Note that size means the height and width of the square
        // Base case, if recursions = 0, return
        if (0 == recursions) return;

        // Draw a black square in the middle square
        float inner = size/3;
        shapeRenderer.rect(x + inner, y + inner, inner, inner);

        // Call punchCantorGasket on all 8 other squares
        punchCantorGasket(x, y, inner, recursions - 1);
        punchCantorGasket(x + inner, y, inner, recursions - 1);
        punchCantorGasket(x + 2*inner, y, inner, recursions - 1);
        punchCantorGasket(x + 2*inner, y + inner, inner, recursions - 1);
        punchCantorGasket(x + 2*inner, y + 2*inner, inner, recursions - 1);
        punchCantorGasket(x + inner, y + 2*inner, inner, recursions - 1);
        punchCantorGasket(x, y + 2*inner, inner, recursions - 1);
        punchCantorGasket(x, y + inner, inner, recursions - 1);
    }

    private Rectangle findLargestSquare(){
        Rectangle largestSquare = new Rectangle();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        if (screenWidth > screenHeight){
            largestSquare.x = (screenWidth - screenHeight)/2;
            largestSquare.y = 0;
            largestSquare.width = screenHeight;
            largestSquare.height = screenHeight;
        } else {
            largestSquare.x = 0;
            largestSquare.y = (screenHeight - screenWidth)/2;
            largestSquare.width = screenWidth;
            largestSquare.height = screenWidth;
        }
        return largestSquare;
    }
}
