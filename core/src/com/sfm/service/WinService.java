package com.sfm.service;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.sfm.entity.Line;
import com.sfm.entity.Respin;
import com.sfm.main.SacuraFortune;


public class WinService extends Group {

    private final Array<Line> lines;
    private final Array<Respin> respins;


    public WinService(){
        lines=new Array<>();
        respins=new Array<>();
        lines.add(new Line(0,156,(int) SacuraFortune.SCREEN_HEIGHT-512-55));
        lines.add(new Line(1,108,(int)SacuraFortune.SCREEN_HEIGHT-169-177));
        lines.add(new Line(2,108,(int)SacuraFortune.SCREEN_HEIGHT-726-177));
        lines.add(new Line(3,138,(int)SacuraFortune.SCREEN_HEIGHT-151-777));
        lines.add(new Line(4,143,(int)SacuraFortune.SCREEN_HEIGHT-152-769));
        lines.add(new Line(5,154,(int)SacuraFortune.SCREEN_HEIGHT-205-256));
        lines.add(new Line(6,145,(int)SacuraFortune.SCREEN_HEIGHT-615-258));
        lines.add(new Line(7,135,(int)SacuraFortune.SCREEN_HEIGHT-419-329));
        lines.add(new Line(8,136,(int)SacuraFortune.SCREEN_HEIGHT-419-329));

        respins.add(new Respin(0,376,(int)SacuraFortune.SCREEN_HEIGHT-395-290));
        respins.add(new Respin(1,324,(int)SacuraFortune.SCREEN_HEIGHT-272-404));
        respins.add(new Respin(2,328,(int)SacuraFortune.SCREEN_HEIGHT-400-279));

        for (Line line:lines)addActor(line);
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
