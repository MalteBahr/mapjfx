package com.sothawo.mapjfx;

public enum LineType {
    POLYGON ("Polygon"),
    LINE_STRING ("LineString");

    private final String polygon;

    LineType(String polygon) {
        this.polygon = polygon;
    }
    @Override
    public String toString(){
        return polygon;
    }
}
