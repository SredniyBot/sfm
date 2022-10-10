package com.sfm.sakura.stages;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.Button;
import com.sfm.sakura.entity.ButtonActivator;
import com.sfm.main.abstracts.HoldButton;
import com.sfm.main.abstracts.InactiveButton;

public class ButtonHandler extends Group {

    ButtonHandler(Viewport viewport,
                  final ButtonReaction buttonReaction,
                  final ButtonActivator buttonActivator){
        addActor(new InactiveButton(viewport) {
            @Override
            public TextureRegion getInactiveTextureRegion() {
                return TextureService.getSpinDisTexture();
            }

            @Override
            public boolean isActive() {
                return buttonActivator.isSpinActive();
            }

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
                return new Rectangle(1348, GameInitializer.SCREEN_HEIGHT-953-127,309,127);
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
                return new Rectangle(1113, GameInitializer.SCREEN_HEIGHT-118,415,118);
            }
        });
        addActor(new InactiveButton(viewport) {
            @Override
            public TextureRegion getInactiveTextureRegion() {
                return TextureService.getAutoDisTexture();
            }

            @Override
            public boolean isActive() {
                return buttonActivator.isAutoActive();
            }

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
                return new Rectangle(1670, GameInitializer.SCREEN_HEIGHT-985-77,218,77);
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
                return new Rectangle(524, GameInitializer.SCREEN_HEIGHT-974-106,113,106);
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
                return new Rectangle(166, GameInitializer.SCREEN_HEIGHT-974-106,113,106);
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
                return new Rectangle(24, GameInitializer.SCREEN_HEIGHT-113,113,113);
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
                return new Rectangle(819, GameInitializer.SCREEN_HEIGHT-113,282,113);
            }
        });
    }

}
