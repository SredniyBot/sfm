package com.sfm.main.abstracts;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.commerce.FIRField;
import com.sfm.commerce.RealButton;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;
import com.sfm.main.game_utils.AutoButton;
import com.sfm.service.CommercialService;
import com.sfm.service.util.GameSound;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

/**
 * Класс кнопок для всех слотов
 *
 */
public class ButtonHandler extends Group implements FIRFieldObserver{

    private final RealButton realActor;
    ButtonHandler(Viewport viewport,
                  final ButtonGuardian buttonGuardian, final ScreenSwitcher switchAction){
        realActor = new RealButton(viewport);// кнопка реал
        addActor(realActor);

        CommercialService.regObserver(this);

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
        });// кнопка спин


        addActor(new AutoButton(viewport,buttonGuardian));// кнопка авто
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
        });// кнопка +
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
        });// кнопка -
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
        });// кнопка назад

//        addActor(new Button(viewport) {
//            @Override
//            public void action() {
//                SoundService.playSound(GameSound.BUTTON);
//            }
//
//            @Override
//            public TextureRegion getTextureRegion() {
//                return null;
//            }
//
//            @Override
//            public Rectangle getRectangle() {
//                return new Rectangle(819, GameInitializer.SCREEN_HEIGHT-113,282,113);
//            }
//        });// кнопка get bonus

    }

    @Override
    public void fieldChanged(FIRField field) {
        realActor.setAvailable(field.isReady);
    }
}
