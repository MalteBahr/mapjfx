package com.sothawo.mapjfx.Styling.Image;

import com.sothawo.mapjfx.Styling.Fill;
import com.sothawo.mapjfx.Styling.Stroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CircleStyle extends ImageStyle{


    private Number opacity;
    private Boolean rotateWithView;
    private Number rotation;
    private Number scale;
    private ArrayList<Number> displacement;

    private Fill fill;
    private Number radius;
    private Stroke stroke;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CircleStyle)) return false;
        if (!super.equals(o)) return false;
        CircleStyle that = (CircleStyle) o;
        return Objects.equals(opacity, that.opacity) &&
                Objects.equals(rotateWithView, that.rotateWithView) &&
                Objects.equals(rotation, that.rotation) &&
                Objects.equals(scale, that.scale) &&
                Objects.equals(displacement, that.displacement) &&
                Objects.equals(fill, that.fill) &&
                Objects.equals(radius, that.radius) &&
                Objects.equals(stroke, that.stroke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), opacity, rotateWithView, rotation, scale, displacement, fill, radius, stroke);
    }

    @Override
    public String toString() {
        return "new ol.style.Circle({" +
                (opacity==null?"":("opacity:" + opacity + ",")) +
                (rotateWithView==null?"":("rotateWithView:" + rotateWithView + "," ))+
                (rotation==null?"":("rotation:" + rotation + "," ))+
                (scale==null?"":("scale:" + scale + "," ))+
                (displacement==null?"":("displacement:" + displacement  + ",")) +
                (fill==null?"":("fill:" + fill + ",")) +
                (radius==null?"":("radius:" + radius + "," ))+
                (stroke==null?"":("stroke:" + stroke)) +
                "})";
    }

    public Number getOpacity() {
        return opacity;
    }

    public void setOpacity(Number opacity) {
        this.opacity = opacity;
    }

    public Boolean getRotateWithView() {
        return rotateWithView;
    }

    public void setRotateWithView(Boolean rotateWithView) {
        this.rotateWithView = rotateWithView;
    }

    public Number getRotation() {
        return rotation;
    }

    public void setRotation(Number rotation) {
        this.rotation = rotation;
    }

    public Number getScale() {
        return scale;
    }

    public void setScale(Number scale) {
        this.scale = scale;
    }

    public List<Number> getDisplacement() {
        return displacement;
    }

    public void setDisplacement(List<Number> displacement) {
        this.displacement = new ArrayList<>(displacement);
    }

    public Fill getFill() {
        return fill;
    }

    public void setFill(Fill fill) {
        this.fill = fill;
    }

    public Number getRadius() {
        return radius;
    }

    public void setRadius(Number radius) {
        this.radius = radius;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    private CircleStyle(CircleStyleBuilder circleStyleBuilder) {
        opacity = circleStyleBuilder.opacity;
        rotateWithView = circleStyleBuilder.rotateWithView;
        rotation = circleStyleBuilder.rotation;
        scale = circleStyleBuilder.scale;
        displacement = circleStyleBuilder.displacement == null?null:new ArrayList<>(circleStyleBuilder.displacement);
        fill = circleStyleBuilder.fill;
        radius = circleStyleBuilder.radius;
        stroke = circleStyleBuilder.stroke;
    }
    private CircleStyle(CircleStyle circleStyle) {
        opacity = circleStyle.opacity;
        rotateWithView = circleStyle.rotateWithView;
        rotation = circleStyle.rotation;
        scale = circleStyle.scale;
        displacement = new ArrayList<>(circleStyle.displacement);
        fill = circleStyle.fill.copy();
        radius = circleStyle.radius;
        stroke = circleStyle.stroke.copy();
    }
    @Override
    public ImageStyle copy() {
        return new CircleStyle(this);
    }

    public static class CircleStyleBuilder implements ImageStyleBuilder<CircleStyleBuilder> {
        private Number opacity;
        private Boolean rotateWithView;
        private Number rotation;
        private Number scale;
        private List<Number> displacement;
        private Fill fill;
        private Number radius;
        private Stroke stroke;





        @Override
        public CircleStyleBuilder setOpacity(Number opacity) {
            this.opacity = opacity;
            return this;
        }                                                     

        @Override
        public CircleStyleBuilder setRotateWithView(boolean b) {
            this.rotateWithView = b;
            return this;
        }

        @Override
        public CircleStyleBuilder setRotation(Number rotation) {
            this.rotation = rotation;
            return this;
        }

        @Override
        public CircleStyleBuilder setScale(Number scale) {
            this.scale = scale;
            return this;
        }

        @Override
        public CircleStyleBuilder setDisplacement(List<Number> displacement) {
            this.displacement = displacement;
            return this;
        }

        public CircleStyleBuilder setFill(Fill fill) {
            this.fill = fill;
            return this;
        }

        public CircleStyleBuilder setRadius(Number radius) {
            this.radius = radius;
            return this;
        }
        public CircleStyleBuilder setStroke(Stroke stroke) {
            this.stroke = stroke;
            return this;
        }

        public CircleStyle build(){
            return new CircleStyle(this);
        }

        }
}
