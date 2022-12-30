package com.sfm.main.game_utils;

import com.sfm.sakura.entity.SakuraType;

public interface BadgeGenerator<T extends BadgeType> {
    T getRandom();
    T getRandomOne();
}
