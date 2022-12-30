package com.sfm.ra.entity;

import com.badlogic.gdx.utils.Array;
import com.sfm.main.game_utils.Badge;
import com.sfm.main.game_utils.BadgeGenerator;
import com.sfm.main.game_utils.BadgeType;
import com.sfm.main.game_utils.Field;

public class RaField<T extends BadgeType<T>> extends Field<T> {

    public RaField( BadgeGenerator<T> badgeGenerator) {
        super( badgeGenerator);
    }
    public int getRespins(Array<Badge<T>> badges){
        BadgeType<T> raType =findFirstBadgeType(badges);
        int i=badgeCount(raType,badges);
        if (raType == RaType.MAN) {
            if (i >= 2) {
                if (i==2){
                  return 1;
                } else if (i == 3) {
                    return 2;
                } else if (i == 4) {
                    return 3;
                } else if (i == 5) {
                    return 4;
                }
            }
        }
        return 0;
    }

    public BadgeType<T> findFirstBadgeType(Array<Badge<T>> badges){
        for (Badge<T> badge:badges){
            if (badge.getBadgeType()!= RaType.BOOK)return badge.getBadgeType();
        }
        return (BadgeType<T>) RaType.BOOK;
    }

    public int badgeCount(BadgeType<T> raType, Array<Badge<T>> badges){
        int i=0;
        for (Badge<T> badge:badges){
            if (badge.getBadgeType()== raType||badge.getBadgeType()== RaType.BOOK)
                i++;
            else return i;
        }
        return i;
    }
}
