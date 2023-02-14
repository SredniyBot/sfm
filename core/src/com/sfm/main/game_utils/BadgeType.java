package com.sfm.main.game_utils;


/**
 * При создании нового слота нужно реализовать этот класс в enum новых карточек
 * Смотри классы, реализующие интерфейс
 *
 * @param <T> класс, реализующий этот метод
 */
public interface BadgeType<T extends BadgeType> {

    /**
     * @return отступ у карточки сверху
     */
    int getPaddingT();

    /**
     * @return отступ у карточки с лева
     */
    int getPaddingL();

    /**
     * @return вертикальный размер карточки в размерах слота (1/2/3)
     */
    int getSize();
    T getType();

    /**
     * количество кадров в карточке
     * @return
     */
    int getNumberOfFrames();

    /**
     * @return интервал между анимациями
     */
    float getAnimationInterval();
    int getId();

    String getAtlas();

    String getName(int frameNumber);

    /**
     * @return цена двух подряд идущих карточек
     */
    float getCost2();

    /**
     * @return цена трех подряд идущих карточек
     */

    float getCost3();

    /**
     * @return цена четырех подряд идущих карточек
     */

    float getCost4();

    /**
     * @return цена пяти подряд идущих карточек
     */

    float getCost5();

}
