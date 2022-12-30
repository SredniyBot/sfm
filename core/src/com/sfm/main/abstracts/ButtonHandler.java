package com.sfm.main.abstracts;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;
import com.sfm.service.GameSound;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

public class ButtonHandler extends Group {

    ButtonHandler(Viewport viewport,
                  final ButtonGuardian buttonGuardian, final ScreenSwitcher switchAction){
        addActor(new InactiveButton(viewport) {
            @Override
            public TextureRegion getInactiveTextureRegion() {
                return TextureService.getTextureRegion("utilities/util/util.atlas","spinN");
            }

            @Override
            public boolean isActive() {
                return !buttonGuardian.isLocked();
            }

            @Override
            public void action() {

                if (buttonGuardian.startSpin()){
                    SoundService.playSound(GameSound.SPIN);
                }
            }

            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("utilities/util/util.atlas","spin");
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1348, GameInitializer.SCREEN_HEIGHT-953-127,309,127);
            }
        });
        addActor(new Button(viewport) {
            @Override
            public void action() {
                SoundService.playSound(GameSound.BUTTON);
            }

            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("utilities/util/util.atlas","real");
            }

            @Override
            public Rectangle getRectangle() {
                return new Rectangle(1113, GameInitializer.SCREEN_HEIGHT-118,415,118);
            }
        });
        addActor(new AutoButton(viewport,buttonGuardian));
        addActor(new HoldButton(viewport) {
            @Override
            public void action() {
                SoundService.playSound(GameSound.BUTTON);
                buttonGuardian.increaseBet(true);
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
                SoundService.playSound(GameSound.BUTTON);
                buttonGuardian.increaseBet(false);
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
        addActor(new InactiveButton(viewport) {
            @Override
            public TextureRegion getInactiveTextureRegion() {
                return TextureService.getTextureRegion("utilities/util/util.atlas","back");
            }

            @Override
            public boolean isActive() {
                return !buttonGuardian.isLocked();
            }

            @Override
            public void action() {
                if(!buttonGuardian.isLocked()) {
                    SoundService.playSound(GameSound.BUTTON);
                    buttonGuardian.cancel();
                    switchAction.switchScreen(ScreenType.MAIN_MENU);
                }
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
                SoundService.playSound(GameSound.BUTTON);
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
