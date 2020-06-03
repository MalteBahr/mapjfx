package com.sothawo.mapjfx.Styling;

import com.sothawo.mapjfx.Styling.Image.CircleStyle;
import com.sothawo.mapjfx.Styling.Image.ImageStyle;
import com.sothawo.mapjfx.Styling.Image.ImageStyleBuilder;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class Style implements Serializable {
    private String geometryString;
    private String renderer;
    private String text;
    private Integer zIndex;

    private ImageStyle image;
    private Stroke stroke;
    private Fill fill;


    private Style(Style style){
        this.geometryString = style.geometryString;
        this.fill = style.fill == null?null:style.fill.copy();
        this.image = style.image == null?null:style.image.copy();
        this.renderer = style.renderer;
        this.stroke = style.stroke == null?null:style.stroke.copy();
        this.text = style.text;
        this.zIndex = style.zIndex;
    }


    public Fill getFill() {
        return fill;
    }

    public ImageStyle getImage() {
        return image;
    }

    public Stroke getStroke() {
        return stroke;
    }

    @Override
    public String toString() {
        return "new ol.style.Style({" +
                (geometryString==null?"":("geometry:" + geometryString + ", ")) +
                (fill==null?"":("fill:" + fill + ", ")) +
                (image==null?"":("image:" + image + ", ")) +
                (renderer==null?"":("renderer:'" + renderer + "', ")) +
                (stroke==null?"":("stroke:" + stroke + ", ")) +
                (text==null?"":("text:'" + text + "', ")) +
                (zIndex==null?"":("zIndex:" + zIndex )) +
                "})";
    }

    private Style(String geometryString, Fill fill, ImageStyle image, String renderer, Stroke stroke, String text, Integer zIndex) {
        this.geometryString = geometryString;
        this.fill = fill;
        this.image = image;
        this.renderer = renderer;
        this.stroke = stroke;
        this.text = text;
        this.zIndex = zIndex;
    }


    public static Style DEFAULT_STYLE(){
        return new Style.StyleBuilder()
                .setStroke(
                        new Stroke.StrokeBuilder(1.25)
                            .setColor(Color.rgb(51,153,204))
                            .build()
                ).setFill(
                        new Fill(Color.rgb(255,255,255,0.4))
                ).setImage(
                        new CircleStyle.CircleStyleBuilder()
                            .setRadius(5)
                            .setFill(new Fill(
                                    Color.rgb(255,255,255,0.4))
                            ).setStroke(
                                    new Stroke.StrokeBuilder(1.25)
                                        .setColor(Color.rgb(51,153,204))
                                        .build()
                            ).build()
                ).build();
    }



    public static Style DEFAULT_SELECTED_STYLE(){
        return new Style.StyleBuilder()
                .setStroke(
                        new Stroke.StrokeBuilder(2)
                                .setColor(Color.rgb(51,153,204))
                                .build()
                ).setFill(
                        new Fill(Color.rgb(0,0,255,0.7))
                ).setImage(
                        new CircleStyle.CircleStyleBuilder()
                                .setRadius(5)
                                .setFill(new Fill(
                                        Color.rgb(255,255,255,1))
                                ).setStroke(
                                new Stroke.StrokeBuilder(1.25)
                                        .setColor(Color.BLUE)
                                        .build()
                        ).build()
                ).build();
    }


    public Style copy(){
        return new Style(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Style)) return false;
        Style style = (Style) o;
        return Objects.equals(geometryString, style.geometryString) &&
                Objects.equals(renderer, style.renderer) &&
                Objects.equals(text, style.text) &&
                Objects.equals(zIndex, style.zIndex) &&
                Objects.equals(image, style.image) &&
                Objects.equals(stroke, style.stroke) &&
                Objects.equals(fill, style.fill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(geometryString, renderer, text, zIndex, image, stroke, fill);
    }

    public static class StyleBuilder{
        private String geometry = null;
        private Fill fill = null;
        private ImageStyle image = null;
        private String renderer = null;
        private Stroke stroke = null;
        private String text = null;
        private Integer zIndex = null ;

        public StyleBuilder setImage(ImageStyle image) {
            this.image = image;
            return this;
        }

        public StyleBuilder setRenderer(String renderer) {
            this.renderer = renderer;
            return this;
        }

        public StyleBuilder setStroke(Stroke stroke) {
            this.stroke = stroke;
            return this;
        }

        public StyleBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public StyleBuilder setzIndex(int zIndex) {
            this.zIndex = zIndex;
            return this;
        }



        public StyleBuilder setFill(Fill fill) {
            this.fill = fill;
            return this;
        }

        public StyleBuilder setGeometry(String geometry){
            this.geometry = geometry;
            return this;
        }

        public Style build(){
            return new Style(geometry,fill,image,renderer,stroke,text,zIndex);
        }

    }
}
