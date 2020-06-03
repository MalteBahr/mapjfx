package com.sothawo.mapjfx.Styling.Image;

import com.sothawo.mapjfx.Styling.ColorUtils;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;

public class Icon extends ImageStyle{


    // inherited


    //new
    private ArrayList<Number> anchor;
    private Origin anchorOrigin;
    private AnchorUnits anchorXUnits;
    private AnchorUnits anchorYUnits;

    private String crossOrigin;
    private ArrayList<Number> offset;
    private Origin offsetOrigin;
    private String img;
    private String size;
    private String imgSize;

    private String src;



    //Non-Serializable -> Custom read/write method
    private transient Color color;

    /**
     * Saves the state of the {@code Icon} instance to a stream
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
        if (!(o instanceof Icon)) return false;
        if (!super.equals(o)) return false;
        Icon icon = (Icon) o;
        return Objects.equals(anchor, icon.anchor) &&
                anchorOrigin == icon.anchorOrigin &&
                anchorXUnits == icon.anchorXUnits &&
                anchorYUnits == icon.anchorYUnits &&
                Objects.equals(crossOrigin, icon.crossOrigin) &&
                Objects.equals(offset, icon.offset) &&
                offsetOrigin == icon.offsetOrigin &&
                Objects.equals(img, icon.img) &&
                Objects.equals(size, icon.size) &&
                Objects.equals(imgSize, icon.imgSize) &&
                Objects.equals(src, icon.src) &&
                Objects.equals(color, icon.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), anchor, anchorOrigin, anchorXUnits, anchorYUnits, crossOrigin, offset, offsetOrigin, img, size, imgSize, src, color);
    }

    @Override
    public String toString() {
        return "new ol.style.Icon({" +
                "opacity:" + opacity +
                ", rotateWithView:" + rotateWithView +
                ", rotation:" + rotation +
                ", scale:" + scale +
                ", displacement:" + displacement +
                ", anchor:" + anchor +
                ", anchorOrigin:" + '"'+anchorOrigin+'"' +
                ", anchorXUnits:" + '"'+anchorXUnits+'"' +
                ", anchorYUnits:" + '"'+anchorYUnits+'"' +
                ", color:" + ColorUtils.toSRGBString(color) +
                ", crossOrigin:'" + crossOrigin + '\'' +
                ", offset:" + offset +
                ", offsetOrigin:" + offsetOrigin +
                ", img:" + img +
                ", size:'" + size + '\'' +
                ", imgSize:'" + imgSize + '\'' +
                ", src:'" + src + '\'' +
                "})";
    }

    private Icon(IconBuilder iconBuilder) {
        super();
        this.opacity = iconBuilder.opacity;
        this.rotateWithView = iconBuilder.rotateWithView;
        this.rotation = iconBuilder.rotation;
        this.scale = iconBuilder.scale;
        this.displacement = iconBuilder.displacement;
        this.anchor = iconBuilder.anchor;
        this.anchorOrigin = iconBuilder.anchorOrigin;
        this.anchorXUnits = iconBuilder.anchorXUnits;
        this.anchorYUnits = iconBuilder.anchorYUnits;
        this.color = iconBuilder.color;
        this.crossOrigin = iconBuilder.crossOrigin;
        this.offset = iconBuilder.offset;
        this.offsetOrigin = iconBuilder.offsetOrigin;
        this.img = iconBuilder.img;
        this.size = iconBuilder.size;
        this.imgSize = iconBuilder.imgSize;
        this.src = iconBuilder.src;
    }


    public Icon(Icon other) {
        super();
        this.opacity = other.opacity;
        this.rotateWithView = other.rotateWithView;
        this.rotation = other.rotation;
        this.scale = other.scale;
        this.displacement = other.displacement==null?null:new ArrayList<>(other.displacement);
        this.anchor = new ArrayList<>(other.anchor);
        this.anchorOrigin = other.anchorOrigin;
        this.anchorXUnits = other.anchorXUnits;
        this.anchorYUnits = other.anchorYUnits;
        this.color = other.color;
        this.crossOrigin = other.crossOrigin;
        this.offset = other.offset==null?null:new ArrayList<>(other.offset);
        this.offsetOrigin = other.offsetOrigin;
        this.img = other.img;
        this.size = other.size;
        this.imgSize = other.imgSize;
        this.src = other.src;
    }
    @Override
    public ImageStyle copy() {
        return new Icon(this);
    }

    public static class IconBuilder implements ImageStyleBuilder<IconBuilder> {

        // inherited
        private Number opacity = 1;
        private boolean rotateWithView = false;
        private Number rotation = 0;
        private Number scale = 1 ;
        private ArrayList<Number> displacement = new ArrayList<>(List.of(0,0));


        //new
        private ArrayList<Number> anchor ;
        private Origin anchorOrigin ;
        private AnchorUnits anchorXUnits ;
        private AnchorUnits anchorYUnits ;
        private Color color;
        private String crossOrigin;
        private ArrayList<Number> offset ;
        private Origin offsetOrigin ;
        public String img;
        private String size;
        private String imgSize;

        private String src;


        @Override
        public IconBuilder setOpacity(Number opacity) {
            this.opacity = opacity;
            return this;
        }

        @Override
        public IconBuilder setRotateWithView(boolean b) {
            this.rotateWithView = b;
            return this;
        }

        @Override
        public IconBuilder setRotation(Number rotation) {
            this.rotation = rotation;
            return this;
        }

        @Override
        public IconBuilder setScale(Number scale) {
            this.scale = scale;
            return this;
        }

        @Override
        public IconBuilder setDisplacement(List<Number> displacement) {
            this.displacement = new ArrayList<>(displacement);
            return this;
        }

        public Icon build(){
            return new Icon(this);
        }
    }


    public enum Origin {
        TOP_LEFT("top-left"),
        TOP_RIGHT("top-right"),
        BOTTOM_RIGHT("bottom-right"),
        BOTTOM_LEFT("bottom-left");

        private final String s;

        @Override
        public String toString() {
            return s;
        }

        Origin(String s) {
            this.s = s;
        }
    }
    public enum AnchorUnits{
        FRACTION("fraction"),
        PIXELS("pixels");

        private final String unit;

        @Override
        public String toString() {
            return unit;
        }

        AnchorUnits(String unit) {
            this.unit = unit;
        }
    }

}

