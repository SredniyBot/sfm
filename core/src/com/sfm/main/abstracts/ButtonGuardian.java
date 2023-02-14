package com.sfm.main.abstracts;

/**
 * Интерфейс взаимодействия поля с кнопками
 */
public interface ButtonGuardian {

    boolean startSpin();
    boolean setAutoRespin(boolean b);
    void increaseBet(boolean inc);
    boolean isLocked();
    void cancel();
}
