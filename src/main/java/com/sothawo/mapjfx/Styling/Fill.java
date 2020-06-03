package com.sothawo.mapjfx.Styling;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Objects;

public class Fill implements Serializable {
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        Objects.requireNonNull(this.color = color);
    }


    //Non-Serializable -> Custom read/write method
    private transient Color color;

    /**
     * Saves the state of the {@code Fill} instance to a stream
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




    public Fill(Color color){
        this.color = color;
    }
    public Fill(){
        this.color = null;
    }


    @Override
    public String toString() {
        return "new ol.style.Fill({" +
                (color==null?"":("color:" + ColorUtils.toSRGBString(this.color)))+
                "})";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fill)) return false;
        Fill fill = (Fill) o;
        return Objects.equals(color, fill.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public Fill copy() {
        return new Fill(this.color);
    }


    public static class FillBuilder{
        private Color color = null;

        public FillBuilder setColor(Color color) {
            this.color = color;
            return this;
        }
        public Fill build(){
            return new Fill(color);
        }
    }

}
