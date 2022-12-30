package com.sfm.ra.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.Background;
import com.sfm.main.abstracts.ButtonGuardian;
import com.sfm.main.game_utils.Field;
import com.sfm.ra.entity.RaType;
import com.sfm.ra.entity.RaField;
import com.sfm.service.TextureService;

public class RaStage extends Stage {

    private final Field<RaType> field;


    public RaStage(Viewport viewport) {
        setViewport(viewport);
        field = new RaField<>( RaType.getBadgeGenerator());

        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("tom_of_ra/usual/usual.atlas","supBackground");
            }
        });
        addActor(field);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("tom_of_ra/usual/usual.atlas","frames");
            }
        });
    }
    public ButtonGuardian getButtonGuardian(){
        return field;
    }
}
