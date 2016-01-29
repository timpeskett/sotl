package net.thirteen.sotl.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Sprite {

    public Enemy() {
        super(new Texture(Gdx.files.internal("enemy.png")));
    }

}

