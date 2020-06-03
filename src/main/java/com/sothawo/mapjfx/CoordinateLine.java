/*
 Copyright 2015-2019 Peter-Josef Meisch (pj.meisch@sothawo.com)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.sothawo.mapjfx;

import com.sothawo.mapjfx.Styling.RootStyle;
import com.sothawo.mapjfx.Styling.Style;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static java.util.Objects.*;

/**
 * A sequence of Coordinate objects which is drawn on the map as a line connecting the coordinates. It has a visible
 * property that enables to switch the visibility on the map off without removing the CoordinateLine frmthe map.
 * Invisible CoordinateLines can easily be switched to visiblae again.
 *
 * @author P.J.Meisch (pj.meisch@sothawo.com)
 */
public class CoordinateLine extends MapElement {
    /** counter for creating the id */
    private final static AtomicLong nextId = new AtomicLong(1);
    /** unique id for this object */
    private final String id;
    /** the coordinates of the line */
    protected final ObservableList<Coordinate> coordinates = FXCollections.observableArrayList();
    protected final IntegerProperty userChanges = new SimpleIntegerProperty(0);
    protected final IntegerProperty rootstyle_changes = new SimpleIntegerProperty(0);
    protected final IntegerProperty selected_rootstyle_changes = new SimpleIntegerProperty(0);
    public final BooleanProperty selected = new SimpleBooleanProperty(false);


    public void addListener(ListChangeListener<? super Coordinate> changeListener){
        this.coordinates.addListener(changeListener);
    }

    public void addListener(InvalidationListener changeListener){
        this.coordinates.addListener(changeListener);
    }

    public RootStyle getRootStyle() {
        return rootStyle;
    }

    public RootStyle getSelected_rootStyle() {
        return selected_rootStyle;
    }

    public void setRootStyle(RootStyle style){
        this.rootStyle = style;
        rootstyle_changes.unbind();
        rootstyle_changes.set(rootstyle_changes.get()+1);
        rootstyle_changes.bind(style.changesProperty());
    }

    public void setSelectedRootStyle(RootStyle style){
        this.selected_rootStyle = style;
        selected_rootstyle_changes.unbind();
        selected_rootstyle_changes.bind(style.changesProperty());
    }


    protected RootStyle rootStyle ;

    protected  RootStyle selected_rootStyle;

    protected String getStyle() {
        return rootStyle.toString();
    }

    protected String getSelectedStyle(){
        return selected_rootStyle.toString();
    }

    public boolean isSelectable() {
        return selectable.getValue();
    }

    protected BooleanProperty selectableProperty(){return this.selectable;}

    public CoordinateLine setSelectable(boolean selectable) {
        this.selectable.set(selectable);
        return this;
    }

    public BooleanProperty selectable = new SimpleBooleanProperty(false);

    private final LineType lineType;


    /**
     * Creates a CoordinateLine for the given coordinates.
     *
     * @param coordinates
     *         the coordinate objects are copied into an internal ist, so that modifying the passed argument list will
     *         not modify this object.
     * @throws java.lang.NullPointerException
     *         if coordinates is null
     */

    public CoordinateLine(List<? extends Coordinate> coordinates, LineType lineType){
        this.id = "coordinateline-" + nextId.getAndIncrement();
        this.coordinates.setAll(requireNonNull(coordinates));
        // slightly transparent limegreen
        this.lineType = lineType;
        this.rootStyle = new RootStyle(Style.DEFAULT_STYLE());
        this.selected_rootStyle = new RootStyle(Style.DEFAULT_STYLE());
        System.out.println("Default Style: " + this.rootStyle);

    }

    public CoordinateLine(List<? extends Coordinate> coordinates) {
        this(coordinates,LineType.POLYGON);
    }

    /**
     * Creates a CoordinateLine for the given coordinates.
     *
     * @param coordinates
     *         the coordinate objects are copied into an internal ist, so that modifying the passed argument list will
     *         not modify this object.
     * @throws java.lang.NullPointerException
     *         if coordinates is null
     */
    public CoordinateLine(final Coordinate... coordinates) {
        this(Arrays.asList(requireNonNull(coordinates)));
    }
    public CoordinateLine(LineType lineType, final Coordinate... coordinates) {
        this(Arrays.asList(requireNonNull(coordinates)),lineType);
    }



    public String getId() {
        return id;
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinateLine that = (CoordinateLine) o;

        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return "CoordinateLine{" +
                "id='" + id + '\'' +
                ", coordinates=" + coordinates +
                ", style=" + rootStyle +
                ", selected style=" + selected_rootStyle +
                "} " + super.toString();
    }

    @Override
    public CoordinateLine setVisible(boolean visible) {
        return (CoordinateLine) super.setVisible(visible);
    }

    /**
     * @return the coordinates as stream. The coordinates are only available as stream, this prevents modification of
     * the internal list.
     */
    public Stream<Coordinate> getCoordinateStream() {
        return coordinates.stream();
    }

    public void setCoordinates(List<Coordinate> newCoordinates) {
      setCoordinatesNoChange(newCoordinates);
      userChanges.setValue(userChanges.get()+1);
    }
    protected void setCoordinatesNoChange(List<Coordinate> newCoordinates){
        System.out.println("changed CoordinateLine " + id);
        System.out.println("old: " +this.coordinates);
        this.coordinates.setAll(requireNonNull(newCoordinates));

        System.out.println("new: " +this.coordinates);
    }

    public LineType getLineType() {
        return lineType;
    }






}
