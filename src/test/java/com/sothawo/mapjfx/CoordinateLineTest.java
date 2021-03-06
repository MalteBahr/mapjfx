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
package com.sothawo.mapjfx;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for CoordinateLine.
 *
 * @author P.J.Meisch (pj.meisch@sothawo.com)
 */
public class CoordinateLineTest implements TestBase {





    @Test
    public void defaultVisibilityIsFalse() throws Exception {
        assertThat(new CoordinateLine().getVisible()).isFalse();
    }


    @Test
    public void noCoordinatesInCtorYieldsEmptyStream() throws Exception {
        final CoordinateLine coordinateLine = new CoordinateLine();
        assertThat(coordinateLine.getCoordinateStream().count()).isEqualTo(0);
    }

    @Test
    public void passedCoordinatesAreInStream() throws Exception {
        final CoordinateLine coordinateLine = new CoordinateLine(coordKarlsruheHarbour, coordKarlsruheStation);
        final Set<Coordinate> coordinates = coordinateLine.getCoordinateStream().collect(Collectors.toSet());
        assertThat(coordinates.contains(coordKarlsruheHarbour)).isTrue();
        assertThat(coordinates.contains(coordKarlsruheStation)).isTrue();
        assertThat(coordinates.size()).isEqualTo(2);
    }

    @Test
    public void setVisibility() throws Exception {
        final CoordinateLine coordinateLine = new CoordinateLine();
        coordinateLine.setVisible(true);
        assertThat(coordinateLine.getVisible()).isTrue();
    }


}
