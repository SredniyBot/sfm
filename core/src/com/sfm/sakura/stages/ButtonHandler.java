package com.sfm.sakura.stages;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;
import com.sfm.main.abstracts.Button;
import com.sfm.sakura.entity.ButtonActivator;
import com.sfm.main.abstracts.HoldButton;
import com.sfm.main.abstracts.InactiveButton;
import com.sfm.service.TextureService;

public class ButtonHandler extends Group {

    ButtonHandler(Viewport viewport,
                  final ButtonReaction buttonReaction,
                  final ButtonActivator buttonActivator, final ScreenSwitcher switchAction){
        addActor(new InactiveButton(viewport) {
            @Override
            public TextureRegion getInactiveTextureRegion() {
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","spinN");
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
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","spin");
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
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","real");
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1113, GameInitializer.SCREEN_HEIGHT-118,415,118);
            }
        });
        addActor(new InactiveButton(viewport) {
            @Override
            public TextureRegion getInactiveTextureRegion() {
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","autoN");
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
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","auto");
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
                switchAction.switchScreen(ScreenType.MAIN_MENU);
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
