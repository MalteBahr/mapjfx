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
function CoordinateLine(projections, geometryType, name, feature ) {
    //final feature
    this.feature = feature;
    this.onMap = false;
    // default color opaque red
    this.feature.setStyle([
        /* We are using two different styles for the polygons:
         *  - The first style is for the polygons themselves.
         *  - The second style is to draw the vertices of the polygons.
         *    In a custom `geometry` function the vertices of a polygon are
         *    returned as `MultiPoint` geometry, which will be used to render
         *    the style.
         */
        new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: "green",
                width: 3
            }),
            fill: new ol.style.Fill({
                color: "rgba(0, 0, 240, 0.1)"
            })
        }),
        new ol.style.Style({
            image: new ol.style.Circle({
                radius: 5,
                fill: new ol.style.Fill({
                    color: 'orange'
                })
            }),
            geometry: function (feature) {
                // return the coordinates of the first ring of the polygon

                let coordinates;
                if (feature.getGeometry().getType() === "Polygon") {
                    coordinates = feature.getGeometry().getCoordinates()[0];
                } else if (feature.getGeometry().getType() === "LineString") {
                    coordinates = feature.getGeometry().getCoordinates();
                }
                return new ol.geom.MultiPoint(coordinates);
            }
        })
    ]);

    this.feature.selectedStyle = [
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
                    color: 'red',
                })
            }),
            // geometry: function(feature) {
            //     // return the coordinates of the first ring of the polygon
            //     let coordinates;
            //     if(feature.getGeometry().getType() === "Polygon"){
            //         coordinates = feature.getGeometry().getCoordinates()[0];
            //     }else if(feature.getGeometry().getType() === "LineString"){
            //         coordinates = feature.getGeometry().getCoordinates();
            //     }
            //     return new ol.geom.MultiPoint(coordinates);
            // }
        })
    ];
    this.feature.setId(name);
    // default is not closed
    this.projections = projections;
    this.name = name;
    this.updateCoordinateTable = {};
}

/**
 * @returns {array} the coordinates of this CoordinateLine. Coordinates are in longitude/latitude order.
 */
CoordinateLine.prototype.getCoordinates = function (right){
    if(this.feature.getGeometry().getType() === "Polygon"){
        return  this.feature.getGeometry().getCoordinates(right)[0];
    }else if(this.feature.getGeometry().getType() === "LineString"){
        return this.feature.getGeometry().getCoordinates(right);
    }
};

/**
 * adds a coordinate to the coordinates array
 * @param {number} latitude value in WGS84
 * @param {number} longitude value in WGS84
 */
CoordinateLine.prototype.addCoordinate = function (latitude, longitude) {
    // lat/lon reversion

    if(this.feature.getGeometry().getType() === "Polygon"){
        // _javaConnector.debug("adding coordinate" + this.feature.getGeometry().getCoordinates());
        let arr = this.feature.getGeometry().getCoordinates();
        arr[0].push(this.projections.cFromWGS84([longitude, latitude]))
        this.feature.getGeometry().setCoordinates(arr);
        // _javaConnector.debug("added coordinate" + this.feature.getGeometry().getCoordinates());
    }else if(this.feature.getGeometry().getType() === "LineString"){
        // _javaConnector.debug("adding coordinate L" + this.feature.getGeometry().getCoordinates());
        let arr = this.feature.getGeometry().getCoordinates();
        arr.push(this.projections.cFromWGS84([longitude, latitude]))
        this.feature.getGeometry().setCoordinates(arr);
        // _javaConnector.debug("added coordinate L" + this.feature.getGeometry().getCoordinates());

    }
};




CoordinateLine.prototype.size = function () {
    return this.getCoordinates().length;
};

CoordinateLine.prototype.getCoordinateLongitude = function (i) {
    return this.projections.cToWGS84(this.getCoordinates(false)[i])[0];
};

CoordinateLine.prototype.getCoordinateLatitude = function (i) {
    return this.projections.cToWGS84(this.getCoordinates(false)[i])[1];
};

CoordinateLine.prototype.setName = function(name){this.name = name;};

/**
 * finishes construction of the object and builds the OL Feature based in the coordinates that were set.
 */

CoordinateLine.prototype.setStyleByPath = function(stylePath){
    import(stylePath).then(style =>{
        this.feature.setStyle(style.default["standard"]);
        this.feature.selectedStyle = style.default["selected"];
    }, reason => _javaConnector.debug("setting Style failed, reason:" + reason));
};

CoordinateLine.prototype.setStyle = function(style) {
    _javaConnector.debug("STYLE CHANGED IN JS:");
    _javaConnector.debug(JSON.stringify(style));
    this.feature.setStyle(style);
};

CoordinateLine.prototype.setSelectedStyle = function(style) {
    this.feature.selectedStyle = style;
};



CoordinateLine.prototype.setSelectable = function(selectable){
    this.feature.selectable = selectable;
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



CoordinateLine.prototype.drawShape = function (type){

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
};

CoordinateLine.prototype.addCoordinate2 = function(latitude, longitude, uniqueID){
    this.updateCoordinateTable[uniqueID].push(this.projections.cFromWGS84([longitude, latitude]));
};

CoordinateLine.prototype.commitUpdate = function(uniqueID){
    _javaConnector.debug("last");
    _javaConnector.debug(this.coordinates);
    this.coordinates = this.updateCoordinateTable[uniqueID];
    _javaConnector.debug("next");
    _javaConnector.debug(this.coordinates);
    delete this.updateCoordinateTable[uniqueID];
    _javaConnector.debug(this.coordinates);
    //const type = this.feature.getGeometry().getType();
    if(this.feature.getGeometry().getType() === "Polygon"){
        _javaConnector.debug("POLYGON");
        this.feature.getGeometry().setCoordinates([this.coordinates]);
    }else if(this.feature.getGeometry().getType() === "LineString"){
        _javaConnector.debug("LINESTRING");
        this.feature.getGeometry().setCoordinates(this.coordinates);
    }
};
