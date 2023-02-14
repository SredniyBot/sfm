package com.sfm.sakura.entity;

import static com.sfm.sakura.entity.SakuraType.LADY;
import static com.sfm.sakura.entity.SakuraType.SWORD;

import com.badlogic.gdx.utils.Array;
import com.sfm.main.game_utils.Badge;
import com.sfm.main.game_utils.BadgeGenerator;
import com.sfm.main.game_utils.BadgeType;
import com.sfm.main.game_utils.Field;

/**
 * Реализация Field, комментарии по методам смотри в Field
 * @param <T>
 */
public class SakuraField<T extends BadgeType<T>> extends Field<T> {

    public SakuraField(BadgeGenerator<T> badgeGenerator) {
        super( badgeGenerator);
    }
    public int getRespins(Array<Badge<T>> badges){
        BadgeType<T> sakuraType =findFirstBadgeType(badges);
        int i=badgeCount(sakuraType,badges);
        if (sakuraType == SWORD) {
            if (i >= 3) {
                if (i == 3) {
                    return 1;
                } else if (i == 4) {
                    return 2;
                } else if (i == 5) {
                    return 3;
                }
            }
        }
        return 0;
    }

    public BadgeType<T> findFirstBadgeType(Array<Badge<T>> badges){
        for (Badge<T> badge:badges){
            if (badge.getBadgeType()!= LADY)return badge.getBadgeType();
        }
        return (BadgeType<T>) LADY;
    }

    public int badgeCount(BadgeType<T> sakuraType, Array<Badge<T>> badges){
        int i=0;
        for (Badge<T> badge:badges){
            if (badge.getBadgeType()== sakuraType ||badge.getBadgeType()== LADY)
                i++;
            else return i;
        }
        return i;
    }
}
