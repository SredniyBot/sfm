package com.sfm;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage implements ButtonReaction {

    private Field field;
    private Bet bet;
    private Money money;
    private Win win;
    public GameStage(Viewport viewport) {


        money=new Money();
        win=new Win(money);
        bet=new Bet(money,win);
        field = new Field(bet);
        setViewport(viewport);
        addActor(new Background());
        addActor(field);
        addActor(new TopGround());
        addActor(bet);
        addActor(money);
        addActor(win);
        addActor(new ButtonHandler(viewport,this));
    }


    @Override
    public void spin() {

        field.spin(false);
    }

    @Override
    public void increaseBet(int inc) {
        bet.increaseBet(inc);
    }
}
