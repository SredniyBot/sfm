package com.sfm.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Сервис фона, использование смотреть на примерах
 * подробности читать в wiki
 */
public class FontService {

    private static BitmapFont font;
    private static BitmapFont bigFont;
    public static final String FONT_CHARACTERS = "€№йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public static void init(){
        if (font!=null)font.dispose();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("utilities/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.characters=FONT_CHARACTERS;
        font= generator.generateFont(parameter);
        generator.dispose();
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("utilities/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 120;
        parameter2.characters=FONT_CHARACTERS;
        bigFont= generator2.generateFont(parameter2);
        generator2.dispose();

    }

    public static BitmapFont getFont() {
        return font;
    }
    public static BitmapFont getBigFont(){
        return bigFont;
    }
}
