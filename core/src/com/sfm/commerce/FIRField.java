package com.sfm.commerce;

/**
 * Объект обмена между ФБ и прилой
 */
public class FIRField {
    public Boolean isReady;
    public String url;

    public FIRField(Boolean isReady, String url) {
        this.isReady = isReady;
        this.url = url;
    }

    public FIRField() {
    }
}
