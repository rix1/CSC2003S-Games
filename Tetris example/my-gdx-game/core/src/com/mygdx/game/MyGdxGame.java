package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Layout layout;
    String[] wallPictures;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        layout = new Layout(getWallPictures());
        layout.createLayout();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)0, (float)0, (float)0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        layout.drawGrid();
		batch.end();
	}

    public String[] getWallPictures(){
        wallPictures = new String[4];

        wallPictures[0] = "walls/pink.png";
        wallPictures[1] = "walls/blue.png";
        wallPictures[2] = "walls/green.png";
        wallPictures[3] = "walls/yellow.png";

        return wallPictures;
    }
}
