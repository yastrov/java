/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package concurrenttest;

/**
 *
 * @author yrain
 */
public class VolatileCounter {
    private static volatile Integer i=0;
    public static Integer next() {
        i = i+1;
        return i;
    }
}
