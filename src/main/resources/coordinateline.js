/*
   Copyright 2015-2019 Peter-Josef Meisch (pj.meisch@sothawo.com)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/**
 * CoordinateLine object. A CoordinateLine object contains an array of coordinates which in turn are an array of two numbers.
 * Internally the coordinates are stored in longitude/latitude order, as this is the order expected by OpenLayers.
 */

/**
 * @constructor
 */
function CoordinateLine(projections) {
    this.coordinates = [];
    this.feature = null;
    this.onMap = false;
    // default color opaque red
    this.color = [255, 0, 0, 1];
    // default fill color transparent yellow
    this.fillColor = [255, 255, 0, 0.3];
    // default width 3
    this.width = 3;
    // default is not closed
    this.closed = false;
    this.projections = projections;
    this.name = null;
    this.updateCoordinateTable = {};

}

/**
 * @returns {array} the coordinates of this CoordinateLine. Coordinates are in longitude/latitude order.
 */
CoordinateLine.prototype.getCoordinates = function () {
    return this.coordinates;
};

/**
 * adds a coordinate to the coordinates array
 * @param {number} latitude value in WGS84
 * @param {number} longitude value in WGS84
 */
CoordinateLine.prototype.addCoordinate = function (latitude, longitude) {
    // lat/lon reversion
    this.coordinates.push(this.projections.cFromWGS84([longitude, latitude]));
};

CoordinateLine.prototype.setCoordinates = function (arr) {
    // lat/lon reversion
    _javaConnector.debug(this.coordinates);

    _javaConnector.debug(arr);

    if(typeof arr[0][0][0] == "undefined"){

        this.coordinates = arr;
    }else {
        this.coordinates = arr[0];
    }
    //this.coordinates.push(this.projections.cFromWGS84([longitude, latitude]));
};


CoordinateLine.prototype.size = function () {
    return this.coordinates.length;
};

CoordinateLine.prototype.getCoordinateLongitude = function (i) {
    _javaConnector.debug(this.coordinates[i]);
    return this.projections.cToWGS84(this.coordinates[i])[0];
};

CoordinateLine.prototype.getCoordinateLatitude = function (i) {
    return this.projections.cToWGS84(this.coordinates[i])[1];
};
/**
 * finishes construction of the object and builds the OL Feature based in the coordinates that were set.
 */
CoordinateLine.prototype.seal = function (name) {

    var styles = [
        /* We are using two different styles for the polygons:
         *  - The first style is for the polygons themselves.
         *  - The second style is to draw the vertices of the polygons.
         *    In a custom `geometry` function the vertices of a polygon are
         *    returned as `MultiPoint` geometry, which will be used to render
         *    the style.
         */
        new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: 'blue',
                width: 3
            }),
            fill: new ol.style.Fill({
                color: 'rgba(0, 0, 255, 0.1)'
            })
        }),
        new ol.style.Style({
            image: new ol.style.Circle({
                radius: 5,
                fill: new ol.style.Fill({
                    color: 'orange'
                })
            }),
            geometry: function(feature) {
                // return the coordinates of the first ring of the polygon
                var coordinates = feature.getGeometry().getCoordinates()[0];
                return new ol.geom.MultiPoint(coordinates);
            }
        })
    ];

    if (this.closed) {
        this.feature = new ol.Feature(new ol.geom.Polygon([this.coordinates]));
    } else {
        this.feature = new ol.Feature(new ol.geom.LineString(this.coordinates));
    }

    var style = new ol.style.Style({
        stroke: new ol.style.Stroke({
            width: this.width,
            color: this.color
        }),
        fill: new ol.style.Fill({
            color: this.fillColor
        })
    });
    _javaConnector.debug("line created");
    this.feature.setStyle(styles);
    this.name = name;
    this.feature.setId(this.name);


};

CoordinateLine.prototype.activateListener = function(name){
    // _javaConnector.debug("listener activated");
    //
    // this.feature.on("change", function (evt) {
    //     _javaConnector.debug("feature changed");
    //     _javaConnector.debug(JSON.stringify(evt));
    // });
    // this.feature.on("change:geometry", function(evt){
    //     _javaConnector.debug("geometry changed");
    // });
    // let geometry = this.feature.getGeometry();
    // geometry.on('change', function(evt) {
    //     _javaConnector.debug("this is the changelistener",name);
    // });
};


/**
 * gets the feature for OpenLayers map
 * @return {ol.Feature}
 */
CoordinateLine.prototype.getFeature = function () {
    return this.feature;
};

/**
 * sets the flag wether the feature is shown on the map
 *
 * @param flag
 */
CoordinateLine.prototype.setOnMap = function (flag) {
    this.onMap = flag;
};

/**
 * gets the flag wether the feature is visible on the map
 * @return {boolean}
 */
CoordinateLine.prototype.getOnMap = function () {
    return this.onMap;
};

/**
 * sets the color of the line.
 *
 * @param {number} red 0..255
 * @param {number} green 0..255
 * @param {number} blue 0..255
 * @param {number} alpha 0..1
 */
CoordinateLine.prototype.setColor = function (red, green, blue, alpha) {
    this.color = [red, green, blue, alpha];
};

/**
 * sets the fill color of the line.
 *
 * @param {number} red 0..255
 * @param {number} green 0..255
 * @param {number} blue 0..255
 * @param {number} alpha 0..1
 */
CoordinateLine.prototype.setFillColor = function (red, green, blue, alpha) {
    this.fillColor = [red, green, blue, alpha];
};

/**
 * sets the width of the line
 *
 * @param width
 */
CoordinateLine.prototype.setWidth = function (width) {
    this.width = width;
};

/**
 * sets the closed flag.
 * @param flag
 */
CoordinateLine.prototype.setClosed = function (flag) {
    this.closed = flag
};


CoordinateLine.prototype.startUpdate= function(uniqueID){
    this.updateCoordinateTable[uniqueID] = [];
}

CoordinateLine.prototype.addCoordinate2 = function(latitude, longitude, uniqueID){
    this.updateCoordinateTable[uniqueID].push(this.projections.cFromWGS84([longitude, latitude]));
}

CoordinateLine.prototype.commitUpdate = function(uniqueID){
    _javaConnector.debug("last");
    _javaConnector.debug(this.coordinates);
    this.coordinates = this.updateCoordinateTable[uniqueID];
    _javaConnector.debug("next");
    _javaConnector.debug(this.coordinates);
    delete this.updateCoordinateTable[uniqueID];
    _javaConnector.debug(this.coordinates);
    //const type = this.feature.getGeometry().getType();
    //TODO: LINESTRING
    this.feature.getGeometry().setCoordinates([this.coordinates]);
}
