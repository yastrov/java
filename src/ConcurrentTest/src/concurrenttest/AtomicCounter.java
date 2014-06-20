/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package concurrenttest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author yrain
 */
public class AtomicCounter {
    private static AtomicInteger i = new AtomicInteger(0);
    public static Integer next() {
        return i.addAndGet(1);
    }
}
