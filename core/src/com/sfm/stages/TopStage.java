package com.sfm.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.entity.Background;
import com.sfm.service.TextureService;

public class TopStage extends Stage {


    TopStage(Viewport viewport) {
        setViewport(viewport);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTgTexture();
            }
        });
    }


}
