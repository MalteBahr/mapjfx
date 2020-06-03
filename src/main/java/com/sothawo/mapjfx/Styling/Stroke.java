package com.sothawo.mapjfx.Styling;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stroke implements Serializable {
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        Objects.requireNonNull(this.color = color);
    }

    private LineCap lineCap;
    private LineJoin lineJoin;
    private ArrayList<Number> lineDash;
    private Number lineDashOffset;
    private Number miterLimit;
    private Number width;



    //Non-Serializable -> Custom read/write method
    private transient Color color;

    /**
     * Saves the state of the {@code Stroke} instance to a stream
     * (that is, serializes it).
     *
     * @param s the stream
     * @throws java.io.IOException if an I/O error occurs
     * @serialData serializes JavaFX Color Object
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out element count, and any hidden stuff
        s.defaultWriteObject();

        s.writeDouble(color.getRed());
        s.writeDouble(color.getGreen());
        s.writeDouble(color.getBlue());
        s.writeDouble(color.getOpacity());

    }

    /**
     * Reconstitutes the {@code Icon} instance from a stream (that is,
     * deserializes it).
     * @param s the stream
     * @throws ClassNotFoundException if the class of a serialized object
     *         could not be found
     * @throws java.io.IOException if an I/O error occurs
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {

        // Read in size, and any hidden stuff
        s.defaultReadObject();
        double red = s.readDouble();
        double green = s.readDouble();
        double blue = s.readDouble();
        double opacity = s.readDouble();
        this.color = new Color(red,green,blue,opacity);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stroke)) return false;
        Stroke stroke = (Stroke) o;
        return lineCap == stroke.lineCap &&
                lineJoin == stroke.lineJoin &&
                Objects.equals(lineDash, stroke.lineDash) &&
                Objects.equals(lineDashOffset, stroke.lineDashOffset) &&
                Objects.equals(miterLimit, stroke.miterLimit) &&
                Objects.equals(width, stroke.width) &&
                Objects.equals(color, stroke.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineCap, lineJoin, lineDash, lineDashOffset, miterLimit, width, color);
    }

    @Override
    public String toString() {
        return "new ol.style.Stroke({" +
                (color==null?"":("color:" + ColorUtils.toSRGBString(color)  +","))+
                (lineCap==null?"":("lineCap:" + '"'+lineCap+'"'+",")) +
                (lineJoin==null?"":("lineJoin:" + '"'+lineJoin+'"'+"," ))+
                (lineDash==null?"":("lineDash:" +lineDash + ",")) +
                (lineDashOffset==null?"":("lineDashOffset:" + lineDashOffset +","))+
                (miterLimit==null?"":("miterLimit:" + miterLimit +","))+
                (width==null?"":("width:" + width ))+
                "})";
    }

    private Stroke(Color color, LineCap lineCap, LineJoin lineJoin, List<Number> lineDash, Number lineDashOffset, Number miterLimit, Number width) {
        this.color = color;
        this.lineCap = lineCap;
        this.lineJoin = lineJoin;
        this.lineDash = lineDash==null?null:new ArrayList<>(lineDash);
        this.lineDashOffset = lineDashOffset;
        this.miterLimit = miterLimit;
        this.width = width;
    }

    private Stroke(Stroke stroke){
        this.color = stroke.color;
        this.lineCap = stroke.lineCap;
        this.lineJoin = stroke.lineJoin;
        this.lineDash = stroke.lineDash==null?null:new ArrayList<>(stroke.lineDash);
        this.lineDashOffset = stroke.lineDashOffset;
        this.miterLimit = stroke.miterLimit;
        this.width = stroke.width;
    }

    public Stroke copy() {
        return new Stroke(this);
    }

    public static class StrokeBuilder {
        public StrokeBuilder setColor(Color color) {
            this.color = color;
            return this;
        }

        public StrokeBuilder setLineCap(LineCap lineCap) {
            this.lineCap = lineCap;
            return this;

        }

        public StrokeBuilder setLineJoin(LineJoin lineJoin) {
            this.lineJoin = lineJoin;

            return this;
        }

        public StrokeBuilder setLineDash(List<Number> lineDash) {
            this.lineDash = lineDash;

            return this;
        }

        public StrokeBuilder setLineDashOffset(Number lineDashOffset) {
            this.lineDashOffset = lineDashOffset;

            return this;
        }

        public StrokeBuilder setMiterLimit(Number miterLimit) {
            this.miterLimit = miterLimit;
            return this;
        }


        private Color color = null;
        private LineCap lineCap = null;
        private LineJoin lineJoin = null;
        private List<Number> lineDash = null;
        private Number lineDashOffset = null;
        private Number miterLimit  = null;
        private final Number width;

        public StrokeBuilder(Number width){
            this.width = width;
        }
        public Stroke build(){
            return new Stroke(color,lineCap,lineJoin,lineDash,lineDashOffset,miterLimit,width);
        }

    }


    public enum LineCap{
        ROUND("round"),
        BUTT("butt"),
        SQUARE("square");

        private final String lineCap;

        @Override
        public String toString() {
            return this.lineCap;
        }

        LineCap(String lineCap) {
            this.lineCap = lineCap;
        }
    }
    public enum LineJoin{
        BEVEL("bevel"),
        ROUND("round"),
        MITER("miter");
        private final String lineJoin;

        @Override
        public String toString() {
            return this.lineJoin;
        }

        LineJoin(String lineJoin) {
            this.lineJoin = lineJoin;
        }
    }

}
