package com.sothawo.mapjfx.Styling;

import javafx.scene.paint.Color;

public class ColorUtils{

        public static String toSRGBString(Color color){
            if(color==null)
                return "null";
            return "\"rgba(" + Math.round(color.getRed()*255) + "," + Math.round(color.getGreen()*255)+ "," + Math.round(color.getBlue()*255) + "," + color.getOpacity() + ")\"";
        }

}
