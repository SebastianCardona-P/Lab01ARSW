package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.List;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    
   /**
     * Returns a range of hexadecimal digits of pi using multiple threads.
     * @param start The starting location of the range.
     * @param count The number of digits to return.
     * @param numThreads The number of threads to use.
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int numThreads) {
        if (start < 0 || count < 0 || numThreads <= 0) {
            throw new RuntimeException("Invalid parameters");
        }

        int rangePerThread = count / numThreads; // Divide el trabajo entre los hilos
        int remainingDigits = count % numThreads; // Digitos que sobran

        List<MiniPiDigits> threads = new ArrayList<>();
        byte[] result = new byte[count];

        // Crear los hilos
        for (int i = 0; i < numThreads; i++) {
            int threadStart = start + i * rangePerThread;
            int threadCount = rangePerThread + (i == numThreads - 1 ? remainingDigits : 0);
            MiniPiDigits thread = new MiniPiDigits(threadStart, threadCount);
            threads.add(thread);
        }

        for(MiniPiDigits thread : threads){
            thread.start();
        }

        // Esperar a que todos los hilos terminen
        for (MiniPiDigits thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted", e);
            }
        }

        // Combinar los resultados de los hilos
        int pos = 0;
        for (MiniPiDigits thread : threads) {
            System.arraycopy(thread.getDigits(), 0, result, pos, thread.getDigits().length);
            pos += thread.getDigits().length;
        }

        return result;
    }

}
