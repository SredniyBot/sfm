package com.sfm.menu.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.Button;
import com.sfm.service.FontService;
import com.sfm.service.util.GameSound;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

public class Window extends Group {

    private final TextureRegion textureRegion;
    private boolean visible;
    private final Actor btn;
    private final GlyphLayout layout;

    Window(Viewport viewport){
        textureRegion= TextureService.getTextureRegion("menu/usual/usual.atlas","window");
        layout = new GlyphLayout();
        btn=new Button(viewport) {
            @Override
            public void action() {
                SoundService.playSound(GameSound.BUTTON);
                invisible();
            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(GameInitializer.SCREEN_WIDTH/2f-textureRegion.getRegionWidth()/2f+469,
                        GameInitializer.SCREEN_HEIGHT/2f-textureRegion.getRegionHeight()/2f+62,547,230);
            }
        };
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!visible)return;
        batch.draw(textureRegion, GameInitializer.SCREEN_WIDTH/2f-textureRegion.getRegionWidth()/2f,
                GameInitializer.SCREEN_HEIGHT/2f-textureRegion.getRegionHeight()/2f-50);

        btn.draw(batch,parentAlpha);
        FontService.getBigFont().draw(batch,layout,GameInitializer.SCREEN_WIDTH/2f-450,
                GameInitializer.SCREEN_HEIGHT/2f-textureRegion.getRegionHeight()/2f+650);
    }

    @Override
    public void act(float delta) {
        btn.act(delta);
    }

    public void invisible(){
        visible=false;
    }
    public boolean getVisible(){
        return visible;
    }

    public void show(String title){
        layout.setText(FontService.getBigFont(),String.valueOf(title), Color.valueOf("#ffffff"),900, Align.center,false);
        visible=true;
    }
}
