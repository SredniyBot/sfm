package com.sfm.wolf.entity;

import static com.sfm.wolf.entity.WolfType.PUMA;
import static com.sfm.wolf.entity.WolfType.WOLF;

import com.badlogic.gdx.utils.Array;
import com.sfm.main.game_utils.Badge;
import com.sfm.main.game_utils.BadgeGenerator;
import com.sfm.main.game_utils.BadgeType;
import com.sfm.main.game_utils.Field;


public class WolfField<T extends BadgeType<T>> extends Field<T> {

    public WolfField(BadgeGenerator<T> badgeGenerator) {
        super( badgeGenerator);
    }
    public int getRespins(Array<Badge<T>> badges){
        BadgeType<T> sakuraType =findFirstBadgeType(badges);
        int i=badgeCount(sakuraType,badges);
        if (sakuraType == PUMA) {
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
            if (badge.getBadgeType()!= WOLF)return badge.getBadgeType();
        }
        return (BadgeType<T>) WOLF;
    }

    public int badgeCount(BadgeType<T> sakuraType, Array<Badge<T>> badges){
        int i=0;
        for (Badge<T> badge:badges){
            if (badge.getBadgeType()== sakuraType ||badge.getBadgeType()== WOLF)
                i++;
            else return i;
        }
        return i;
    }
}
