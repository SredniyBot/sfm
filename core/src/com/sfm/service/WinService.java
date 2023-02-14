package com.sfm.service;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.sfm.main.abstracts.Background;
import com.sfm.main.game_utils.Respin;
import com.sfm.main.GameInitializer;
import com.sfm.service.util.Line;

/**
 * Сервис показа респинов, линий побед для слотов и цифр
 */
public class WinService extends Group {
    private final Array<Line> lines;
    private final Array<Respin> respins;

    public WinService(){
        lines=new Array<>();
        respins=new Array<>();
        lines.add(new Line(1,156,(int) GameInitializer.SCREEN_HEIGHT-512-55));
        lines.add(new Line(2,108,(int) GameInitializer.SCREEN_HEIGHT-169-177));
        lines.add(new Line(3,108,(int) GameInitializer.SCREEN_HEIGHT-726-177));
        lines.add(new Line(4,138,(int) GameInitializer.SCREEN_HEIGHT-151-777));
        lines.add(new Line(5,143,(int) GameInitializer.SCREEN_HEIGHT-152-769));
        lines.add(new Line(6,154,(int) GameInitializer.SCREEN_HEIGHT-205-256));
        lines.add(new Line(7,145,(int) GameInitializer.SCREEN_HEIGHT-615-258));
        lines.add(new Line(8,135,(int) GameInitializer.SCREEN_HEIGHT-419-329));
        lines.add(new Line(9,136,(int) GameInitializer.SCREEN_HEIGHT-419-329));

        respins.add(new Respin(0,376,(int) GameInitializer.SCREEN_HEIGHT-395-290));
        respins.add(new Respin(1,324,(int) GameInitializer.SCREEN_HEIGHT-272-404));
        respins.add(new Respin(2,328,(int) GameInitializer.SCREEN_HEIGHT-400-279));
        respins.add(new Respin(3,328,(int) GameInitializer.SCREEN_HEIGHT-400-279));

        for (Line line:lines)addActor(line);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("utilities/lines/lines.atlas","digits");
            }
        });
        for (Respin respin:respins)addActor(respin);
    }

    public void showLine(int lineNumber){
        lines.get(lineNumber).startShowing();
    }

    public void showRespin(int respinNumber){
        respins.get(respinNumber-1).startShowing();
    }

    public boolean isReady() {
        for (Line line:lines)if (line.isDrawing())return false;
        for (Respin respin:respins)if (respin.isDrawing())return false;
        return true;
    }
}
