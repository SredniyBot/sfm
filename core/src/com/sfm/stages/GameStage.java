package com.sfm.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.entity.money.MoneyTracker;
import com.sfm.grounds.BackgroundRam;
import com.sfm.entity.Field;
import com.sfm.grounds.TopGround;
public class GameStage extends Stage implements ButtonReaction {

    private final Field field;

    private final MoneyTracker moneyTracker;
    public GameStage(Viewport viewport) {
        setViewport(viewport);
        moneyTracker=new MoneyTracker();
        field = new Field(moneyTracker);

        addActor(new BackgroundRam());
        addActor(field);
        addActor(new TopGround());
        addActor(moneyTracker);
        addActor(new ButtonHandler(viewport,this));
    }


    @Override
    public void spin() {

        field.spin(false);
    }

    @Override
    public void increaseBet(int inc) {
        moneyTracker.increaseBet(inc);
    }
}
