/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.masterworker.system;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.iluwatar.masterworker.ArrayEquality;
import com.iluwatar.masterworker.ArrayInput;
import com.iluwatar.masterworker.ArrayResult;

/**
* Testing getResult method in ArrayTransposeMasterWorker class.
*/

class ArrayTransposeMasterWorkerTest {

  @Test
  void getResultTest() {
    ArrayTransposeMasterWorker atmw = new ArrayTransposeMasterWorker();
    int[][] matrix = new int[][] {{1,2,3,4,5}, {1,2,3,4,5}, {1,2,3,4,5}, {1,2,3,4,5}, {1,2,3,4,5}};
    int[][] matrixTranspose = new int[][] {{1,1,1,1,1}, {2,2,2,2,2}, {3,3,3,3,3}, {4,4,4,4,4}, {5,5,5,5,5}};
    ArrayInput i = new ArrayInput(matrix);
    ArrayResult r = (ArrayResult) atmw.getResult(i);
    assertTrue(ArrayEquality.matricesSame(r.data, matrixTranspose));
  }
  
}