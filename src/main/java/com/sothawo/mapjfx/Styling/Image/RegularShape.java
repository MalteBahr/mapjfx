package com.sothawo.mapjfx.Styling.Image;

import com.sothawo.mapjfx.Styling.Fill;
import com.sothawo.mapjfx.Styling.Stroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegularShape extends ImageStyle {



    private Number opacity;
    private boolean rotateWithView;
    private Number rotation;
    private Number scale;
    private ArrayList<Number> displacement;
    private Fill fill;
    private Number points;
    private Number radius;
    private Number radius1;
    private Number radius2;
    private Number angle;
    private Stroke stroke;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegularShape)) return false;
        if (!super.equals(o)) return false;
        RegularShape that = (RegularShape) o;
        return rotateWithView == that.rotateWithView &&
                Objects.equals(opacity, that.opacity) &&
                Objects.equals(rotation, that.rotation) &&
                Objects.equals(scale, that.scale) &&
                Objects.equals(displacement, that.displacement) &&
                Objects.equals(fill, that.fill) &&
                Objects.equals(points, that.points) &&
                Objects.equals(radius, that.radius) &&
                Objects.equals(radius1, that.radius1) &&
                Objects.equals(radius2, that.radius2) &&
                Objects.equals(angle, that.angle) &&
                Objects.equals(stroke, that.stroke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), opacity, rotateWithView, rotation, scale, displacement, fill, points, radius, radius1, radius2, angle, stroke);
    }

    @Override
    public String toString() {
        return "new ol.style.RegularShape({" +
                "opacity:" + opacity +
                ", rotateWithView:" + rotateWithView +
                ", rotation:" + rotation +
                ", scale:" + scale +
                ", displacement:" + displacement +
                ", fill:" + fill +
                ", points:" + points +
                ", radius:" + radius +
                ", radius1:" + radius1 +
                ", radius2:" + radius2 +
                ", angle:" + angle +
                ", stroke:" + stroke +
                "})";
    }

    public Number getOpacity() {
        return opacity;
    }

    public void setOpacity(Number opacity) {
        this.opacity = opacity;
    }

    public boolean isRotateWithView() {
        return rotateWithView;
    }

    public void setRotateWithView(boolean rotateWithView) {
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

    public Number getPoints() {
        return points;
    }

    public void setPoints(Number points) {
        this.points = points;
    }

    public Number getRadius() {
        return radius;
    }

    public void setRadius(Number radius) {
        this.radius = radius;
    }

    public Number getRadius1() {
        return radius1;
    }

    public void setRadius1(Number radius1) {
        this.radius1 = radius1;
    }

    public Number getRadius2() {
        return radius2;
    }

    public void setRadius2(Number radius2) {
        this.radius2 = radius2;
    }

    public Number getAngle() {
        return angle;
    }

    public void setAngle(Number angle) {
        this.angle = angle;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    private  RegularShape(RegularShapeBuilder regularShapeBuilder) {
        opacity = regularShapeBuilder.opacity;
        rotateWithView = regularShapeBuilder.rotateWithView;
        rotation = regularShapeBuilder.rotation;
        scale = regularShapeBuilder.scale;
        displacement = regularShapeBuilder.displacement;
        fill = regularShapeBuilder.fill;
        points = regularShapeBuilder.points;
        radius = regularShapeBuilder.radius;
        radius1 = regularShapeBuilder.radius1;
        radius2 = regularShapeBuilder.radius2;
        angle = regularShapeBuilder.angle;
        stroke = regularShapeBuilder.stroke;
    }

    private  RegularShape(RegularShape regularShape) {
        opacity = regularShape.opacity;
        rotateWithView = regularShape.rotateWithView;
        rotation = regularShape.rotation;
        scale = regularShape.scale;
        displacement = new ArrayList<>(regularShape.displacement);
        fill = regularShape.fill.copy();
        points = regularShape.points;
        radius = regularShape.radius;
        radius1 = regularShape.radius1;
        radius2 = regularShape.radius2;
        angle = regularShape.angle;
        stroke = regularShape.stroke.copy();
    }


    @Override
    public ImageStyle copy() {
        return new RegularShape(this);
    }

    public static class RegularShapeBuilder implements ImageStyleBuilder<RegularShapeBuilder> {


        private Number opacity;
        private boolean rotateWithView = false;
        private Number rotation = 0;
        private Number scale;
        private ArrayList<Number> displacement = new ArrayList<>(List.of(0,0));
        private Fill fill;
        private Number points;
        private Number radius;
        private Number radius1;
        private Number radius2;
        private Number angle=0;
        private Stroke stroke;

        public RegularShapeBuilder setFill(Fill fill) {
            this.fill = fill;
            return this;
        }

        public RegularShapeBuilder setPoints(Number points) {
            this.points = points;
            return this;
        }

        public RegularShapeBuilder setRadius(Number radius) {
            this.radius = radius;
            return this;
        }

        public RegularShapeBuilder setRadius1(Number radius1) {
            this.radius1 = radius1;
            return this;
        }

        public RegularShapeBuilder setRadius2(Number radius2) {
            this.radius2 = radius2;
            return this;
        }

        public RegularShapeBuilder setAngle(Number angle) {
            this.angle = angle;
            return this;
        }

        public RegularShapeBuilder setStroke(Stroke stroke) {
            this.stroke = stroke;
            return this;
        }

        public RegularShape build(){
            return new RegularShape(this);
        }

        @Override
        public RegularShapeBuilder setOpacity(Number opacity) {
            this.opacity = opacity;
            return this;
        }

        @Override
        public RegularShapeBuilder setRotateWithView(boolean b) {
            this.rotateWithView = b;
            return this;
        }

        @Override
        public RegularShapeBuilder setRotation(Number rotation) {
            this.rotation = rotation;
            return this;
        }

        @Override
        public RegularShapeBuilder setScale(Number scale) {
            this.scale = scale;
            return this;
        }

        @Override
        public RegularShapeBuilder setDisplacement(List<Number> displacement) {
            this.displacement = new ArrayList<>(displacement);
            return this;
        }
    }
}

