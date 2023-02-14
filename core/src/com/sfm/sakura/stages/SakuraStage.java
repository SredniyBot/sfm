package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.abstracts.Background;
import com.sfm.main.abstracts.ButtonGuardian;
import com.sfm.main.game_utils.Field;
import com.sfm.sakura.entity.SakuraField;
import com.sfm.sakura.entity.SakuraType;
import com.sfm.service.TextureService;

/**
 * Класс стейджа слота сакуры
 */
public class SakuraStage extends Stage  {

    private final Field<SakuraType> field;

    public SakuraStage(Viewport viewport) {
        setViewport(viewport);
        field = new SakuraField<>(SakuraType.getBadgeGenerator());

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

    public ButtonGuardian getButtonGuardian(){
        return field;
    }

}
