package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.abstracts.Background;
import com.sfm.main.money.MoneyTracker;
import com.sfm.sakura.entity.Field;

public class GameStage extends Stage implements ButtonReaction {

    private final Field field;

    private final MoneyTracker moneyTracker;
    public GameStage(Viewport viewport) {
        setViewport(viewport);
        moneyTracker=new MoneyTracker();
        field = new Field(moneyTracker);

        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getSupBackground();
            }
        });
        addActor(field);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getFrameTexture();
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
