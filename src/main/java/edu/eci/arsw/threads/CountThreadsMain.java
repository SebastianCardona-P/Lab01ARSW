
package edu.eci.arsw.threads;

public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread hilo1 = new CountThread(0, 99);
        CountThread hilo2 = new CountThread(99, 199);
        CountThread hilo3 = new CountThread(200, 299);

        /*
         hilo1.start();
         hilo2.start();
         hilo3.start();
         */
        hilo1.run();
        hilo2.run();
        hilo3.run();
    }
    
}
