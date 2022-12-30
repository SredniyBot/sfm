package com.sfm.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.Background;
import com.sfm.main.abstracts.Button;

public class LoaderStage extends Stage implements Runnable {

    private Runnable runnable;
    LoaderStage(Viewport viewport, final TextureAtlas textureAtlas) {
        setViewport(viewport);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return textureAtlas.findRegion("front");
            }
        });
        addActor(new ProgressBar(textureAtlas,this));
        addActor(new PrivacyActor(textureAtlas.findRegion("text")));
        addActor(new Button(viewport) {
            @Override
            public void action() {
                Gdx.net.openURI("https://en.wikipedia.org/wiki/Privacy_policy");
            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1408, GameInitializer.SCREEN_HEIGHT-1050,310,50);
            }
        });
    }

    public void setEndpointScreen(Runnable runnable) {
        this.runnable=runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }

}
