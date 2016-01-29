package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Hero extends Sprite {

    public Hero() {
        super(new Texture(Gdx.files.internal("hero.png")));
    }

}

