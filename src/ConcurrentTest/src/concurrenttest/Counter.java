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
public class Counter {
    private static Integer i=0;
    public synchronized static int next() {
        return ++i;
    }
}
