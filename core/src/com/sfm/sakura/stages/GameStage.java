package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.Background;
import com.sfm.main.money.MoneyTracker;
import com.sfm.sakura.entity.Field;
import com.sfm.service.TextureService;

public class GameStage extends Stage implements ButtonReaction {

    private final Field field;

    private final MoneyTracker moneyTracker;
    public GameStage(Viewport viewport) {
        setViewport(viewport);
        moneyTracker= GameInitializer.getMoneyTracker();
        moneyTracker.showBet(true);
        moneyTracker.setNX(380);
        field = new Field(moneyTracker);

        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","supBackground");
            }
        });
        addActor(field);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","frames");
            }
        });
    }


    @Override
    public void spin() {
        field.spin(false);
    }

    @Override
    public void increaseBet(int inc) {
        moneyTracker.increaseBet(inc);
    }

    public MoneyTracker getMoneyTracker() {
        return moneyTracker;
    }
}
