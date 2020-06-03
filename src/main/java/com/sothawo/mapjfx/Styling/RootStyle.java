package com.sothawo.mapjfx.Styling;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RootStyle extends ArrayList<Style>{

    public RootStyle (Style... styles){
        super(List.of(styles));
    }
    public RootStyle (Collection<? extends Style> styles){
        super(styles);
    }
    public RootStyle(){};

    // Can be null because properties are not serializable!
    private transient IntegerProperty changes = new SimpleIntegerProperty(0);



    public IntegerProperty changesProperty() {
        if(changes==null)
            changes = new SimpleIntegerProperty(0);
        return changes;
    }
    public RootStyle changed() {
        if(changes==null){
            changes = new SimpleIntegerProperty(0);
        }
        changes.set(changes.get()+1);
        return this;
    }





    /**
     * sets all styles and deletes old styles, issues a change.
     * @param styles
     * @return
     */

    public boolean setAll(List<Style> styles) {
        clear();
        boolean a = addAll(styles);
        changed();
        return a;
    }

    public RootStyle copy(){
        return new RootStyle(this.stream().map(Style::copy).collect(Collectors.toList()));
    }



}
