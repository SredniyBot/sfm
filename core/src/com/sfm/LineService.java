package com.sfm;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;


public class LineService extends Group {

    private final Array<Line> lines;

    LineService(){
        lines=new Array<>();
        lines.add(new Line(0,156,(int)SacuraFortune.SCREEN_HEIGHT-512-55));
        lines.add(new Line(1,108,(int)SacuraFortune.SCREEN_HEIGHT-169-177));
        lines.add(new Line(2,108,(int)SacuraFortune.SCREEN_HEIGHT-726-177));
        lines.add(new Line(3,138,(int)SacuraFortune.SCREEN_HEIGHT-151-777));
        lines.add(new Line(4,143,(int)SacuraFortune.SCREEN_HEIGHT-152-769));
        lines.add(new Line(5,154,(int)SacuraFortune.SCREEN_HEIGHT-205-256));
        lines.add(new Line(6,145,(int)SacuraFortune.SCREEN_HEIGHT-615-258));
        lines.add(new Line(7,135,(int)SacuraFortune.SCREEN_HEIGHT-419-329));
        lines.add(new Line(8,136,(int)SacuraFortune.SCREEN_HEIGHT-419-329));
        for (Line line:lines)addActor(line);
    }

    public void show(int lineNumber){
        lines.get(lineNumber).startShowing();
    }


}
