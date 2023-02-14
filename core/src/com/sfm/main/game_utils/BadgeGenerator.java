package com.sfm.main.game_utils;

/**
 * Интерфейс генерации случайных карточек
 * @param <T> тип карточки
 */
public interface BadgeGenerator<T extends BadgeType> {
    T getRandom();
    T getRandomOne();
}
