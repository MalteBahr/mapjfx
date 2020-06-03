package com.sothawo.mapjfx.Styling.Image;

import java.util.List;

public interface ImageStyleBuilder<T> {
    T setOpacity(Number opacity);
    T setRotateWithView(boolean b);
    T setRotation(Number rotation);
    T setScale(Number scale);
    T setDisplacement(List<Number> displacement);
}
