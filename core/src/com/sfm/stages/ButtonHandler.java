package com.sfm.stages;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.SacuraFortune;
import com.sfm.entity.Button;
import com.sfm.entity.HoldButton;
import com.sfm.service.TextureService;
import com.sfm.stages.ButtonReaction;

public class ButtonHandler extends Group {


    ButtonHandler(Viewport viewport, final ButtonReaction buttonReaction){
        addActor(new Button(viewport) {
            @Override
            public void action() {
                buttonReaction.spin();
            }

            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getSpinTexture();
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1348, SacuraFortune.SCREEN_HEIGHT-953-127,309,127);
            }
        });
        addActor(new Button(viewport) {
            @Override
            public void action() {
                System.out.println("Real");
            }

            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getRealTexture();
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1113,SacuraFortune.SCREEN_HEIGHT-118,415,118);
            }
        });
        addActor(new Button(viewport) {
            @Override
            public void action() {
                System.out.println("Auto");
            }

            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getAutoTexture();
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1670,SacuraFortune.SCREEN_HEIGHT-985-77,218,77);
            }
        });
        addActor(new HoldButton(viewport) {
            @Override
            public void action() {
                buttonReaction.increaseBet(10);
            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(524,SacuraFortune.SCREEN_HEIGHT-974-106,113,106);
            }
        });
        addActor(new HoldButton(viewport) {
            @Override
            public void action() {
                buttonReaction.increaseBet(-10);
            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(166,SacuraFortune.SCREEN_HEIGHT-974-106,113,106);
            }
        });
        addActor(new Button(viewport) {
            @Override
            public void action() {
                System.out.println("back");
            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(24,SacuraFortune.SCREEN_HEIGHT-113,113,113);
            }
        });
        addActor(new Button(viewport) {
            @Override
            public void action() {
                System.out.println("bonus");
            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(819,SacuraFortune.SCREEN_HEIGHT-113,282,113);
            }
        });
    }

}
