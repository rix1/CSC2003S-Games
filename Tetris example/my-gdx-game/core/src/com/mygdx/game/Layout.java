package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by siriholtnaes on 05.08.14.
 */
public class Layout {
    SpriteBatch batch = new SpriteBatch();
    Texture leftWall;
    Texture rightWall;
    Texture bottomWall;
    Texture upperWall;

    float blockW = 20;
    float blockH = 20;

    int gridW = 10;
    int gridH = 20;

    int [][] grid;

    public Layout(String[] filenames) {
        leftWall = new Texture(filenames[0]);
        rightWall = new Texture(filenames[1]);
        bottomWall = new Texture(filenames[2]);
        upperWall = new Texture(filenames[3]);

        grid = new int[gridW][gridH];
    }

    public void createLayout() {
        for (int y = 0; y < gridH; y++) {
            for (int x = 0; x < gridW; x++) {
                if (x == 0) {
                    grid[x][y] = 10;
                }
                if (x == gridW - 1) {
                    grid[x][y] = 1;
                }
                if (y == 0) {
                    grid[x][y] = 2;
                }
                if (y == gridH - 1) {
                    grid[x][y] = 3;
                }
            }
        }
    }

    //MyGdxGame->render->batch begin
    public void drawGrid() {
       batch.begin();
        for (int y = 0; y < gridH; y++) {
            for (int x = 0; x < gridW; x++) {
                if (grid[x][y] == 10) {
                    batch.draw(leftWall, x * blockW, y * blockH);
                    //System.out.println("Y: " + y + " x: " + x + " - Draw pink!");
                }
                if (grid[x][y] == 1) {
                    batch.draw(rightWall, x * blockW, y * blockH);
                }
                if (grid[x][y] == 2) {
                    batch.draw(bottomWall, x * blockW, y * blockH);
                }
                if (grid[x][y] == 3) {
                    batch.draw(upperWall, x * blockW, y * blockH);
                }
            }
        }
        batch.end();
    }
}