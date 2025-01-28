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

   /**
     * Returns a range of hexadecimal digits of pi using multiple threads.
     * @param start The starting location of the range.
     * @param count The number of digits to return.
     * @param N The number of threads to use.
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) {
        if (start < 0 || count < 0 || N <= 0) {
            throw new RuntimeException("Invalid parameters");
        }
        // Dividir el total de hilos entre el número de hilos
        int rangePerThread = count / N; 
        int remainingDigits = count % N; // Tener en cuenta los dígitos restantes

        List<MiniPiDigits> threads = new ArrayList<>(); // Lista de hilos 
        byte[] result = new byte[count]; // lista de resultado en bytes

        // Crear los hilos y agregarlos a la lista
        for (int i = 0; i < N; i++) {
            int threadStart = start + i * rangePerThread;
            int threadCount = rangePerThread + (i == N - 1 ? remainingDigits : 0);
            MiniPiDigits thread = new MiniPiDigits(threadStart, threadCount);
            threads.add(thread);
        }

        // Iniciar los hilos
        for(MiniPiDigits thread : threads){
            thread.start();
        }

        // Esperar a que todos los hilos terminen con el .join()
        for (MiniPiDigits thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted", e);
            }
        }

        // Combinar los resultados de los hilos en un solo array
        int pos = 0;
        for (MiniPiDigits thread : threads) {
            System.arraycopy(thread.getDigits(), 0, result, pos, thread.getDigits().length);
            pos += thread.getDigits().length;
        }

        return result;
    }

}
