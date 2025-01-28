package edu.eci.arsw.math;

public class Main {
    public static void main(String[] args) {
        // Obtener el número de procesadores disponibles
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        
        // Medir el tiempo de ejecución
        long startTime = System.currentTimeMillis();
        
        // Calcular pi con el número n de hilos
        byte[] digits = PiDigits.getDigits(1, 100000, 250);
        
        long endTime = System.currentTimeMillis();
                
        // Imprimir resultado
        System.out.println(bytesToHex(digits));
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + "ms");
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb = new StringBuilder(hexChars.length / 2);
        for (int i = 0; i < hexChars.length; i = i + 2) {
            sb.append(hexChars[i + 1]);
        }
        return sb.toString();
    }
}
