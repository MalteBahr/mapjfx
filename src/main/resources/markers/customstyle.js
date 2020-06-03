
export default {standard: [
    /* We are using two different styles for the polygons:
     *  - The first style is for the polygons themselves.
     *  - The second style is to draw the vertices of the polygons.
     *    In a custom `geometry` function the vertices of a polygon are
     *    returned as `MultiPoint` geometry, which will be used to render
     *    the style.
     */
    new ol.style.Style({
        stroke: new ol.style.Stroke({
            color: "red",
            width: 3
        }),
        fill: new ol.style.Fill({
            color: "rgba(0, 0, 255, 0.1)"
        })
    }),
    new ol.style.Style({
        image: new ol.style.Circle({
            radius: 5,
            fill: new ol.style.Fill({
                color: "orange"
            })
        }),
        geometry: function(feature) {
            // return the coordinates of the first ring of the polygon
            var coordinates = feature.getGeometry().getCoordinates()[0];
            return new ol.geom.MultiPoint(coordinates);
        }
    })
], selected: [
        /* We are using two different styles for the polygons:
         *  - The first style is for the polygons themselves.
         *  - The second style is to draw the vertices of the polygons.
         *    In a custom `geometry` function the vertices of a polygon are
         *    returned as `MultiPoint` geometry, which will be used to render
         *    the style.
         */
        new ol.style.Style({
            zIndex:100,
            stroke: new ol.style.Stroke({
                color: 'darkblue',
                width: 3
            }),
            fill: new ol.style.Fill({
                color: 'rgba(0, 0, 255, 0.1)'
            })
        }),
        new ol.style.Style({
            zIndex:100,
            image: new ol.style.Circle({
                radius: 5,
                fill: new ol.style.Fill({
                    color: 'green',
                })
            }),
            geometry: function(feature) {
                // return the coordinates of the first ring of the polygon
                let coordinates;
                if(feature.getGeometry().getType() === "Polygon"){
                    coordinates = feature.getGeometry().getCoordinates()[0];
                }else if(feature.getGeometry().getType() === "LineString"){
                    coordinates = feature.getGeometry().getCoordinates();
                }
                return new ol.geom.MultiPoint(coordinates);
            }
        })
    ]};
