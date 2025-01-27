
package edu.eci.arsw.threads;

public class CountThread extends Thread{

    private int a;
    private int b;

    public CountThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run(){
        String threadName = Thread.currentThread().getName();
        for (int i = a; i <= b; i++){
            System.out.println( threadName + " " +i);
        }
    }

    public int getA(){
        return a;
    }

    public int getB(){
        return b;
    }
    
}
