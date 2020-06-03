package com.sothawo.mapjfx.Styling.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ImageStyle implements Serializable {
    Number opacity;
    boolean rotateWithView;
    Number rotation;
    Number scale;
    ArrayList<Number> displacement;

    public abstract ImageStyle copy();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageStyle)) return false;
        ImageStyle that = (ImageStyle) o;
        return rotateWithView == that.rotateWithView &&
                Objects.equals(opacity, that.opacity) &&
                Objects.equals(rotation, that.rotation) &&
                Objects.equals(scale, that.scale) &&
                Objects.equals(displacement, that.displacement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opacity, rotateWithView, rotation, scale, displacement);
    }
}
