package com.sfm.service;

import com.badlogic.gdx.Gdx;
import com.sfm.commerce.FIRField;
import com.sfm.main.abstracts.FIRFieldObserver;
import java.util.ArrayList;
import java.util.List;
import pl.mk5.gdx.fireapp.GdxFIRDatabase;
import pl.mk5.gdx.fireapp.functional.Consumer;

public class CommercialService {

    private static List<FIRFieldObserver> observerList;

    public static void init(){
        observerList=new ArrayList<>();
        GdxFIRDatabase.inst()
                .inReference("commercials")
                .onDataChange(FIRField.class)
                .then(new Consumer<FIRField>() {
                    @Override
                    public void accept(FIRField FIRField) {
                        paramsChanged(FIRField);
                    }
                });
    }

    public static void regObserver(final FIRFieldObserver firFieldObserver){
        observerList.add(firFieldObserver);
        GdxFIRDatabase.inst()
                .inReference("commercials")
                .readValue(FIRField.class)
                .then(new Consumer<FIRField>() {
                    @Override
                    public void accept(FIRField field) {
                        firFieldObserver.fieldChanged(field);
                    }
                });
    }

    private static void paramsChanged(FIRField firField){
        for (FIRFieldObserver firFieldObserver:observerList){
            firFieldObserver.fieldChanged(firField);
        }
    }

    public static void openURL(){
        GdxFIRDatabase.inst()
                .inReference("commercials")
                .readValue(FIRField.class)
                .then(new Consumer<FIRField>() {
                    @Override
                    public void accept(FIRField field) {
                        Gdx.net.openURI(field.url);
                    }
                });
    }

}
