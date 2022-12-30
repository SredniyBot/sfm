package com.sfm.wolf.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.abstracts.Background;
import com.sfm.main.abstracts.ButtonGuardian;
import com.sfm.main.game_utils.Field;
import com.sfm.wolf.entity.WolfField;
import com.sfm.wolf.entity.WolfType;
import com.sfm.service.TextureService;

public class WolfStage extends Stage  {

    private final Field<WolfType> field;

    public WolfStage(Viewport viewport) {
        setViewport(viewport);
        field = new WolfField<>(WolfType.getBadgeGenerator());

        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("wolf/usual/usual.atlas","supBackground");
            }
        });
        addActor(field);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("wolf/usual/usual.atlas","frames");
            }
        });
    }

    public ButtonGuardian getButtonGuardian(){
        return field;
    }

}
