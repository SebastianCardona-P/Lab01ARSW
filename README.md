# Laboratorio Hilos y Paralelismo 01

## Autores:
- Sebastián Cardona
- Laura Gil
- Zayra Gutiérrez

## Profesor:
- Javier Toquica Barrero
---

## Descripción

Este informe tiene como objetivo explorar y analizar el uso de **hilos**, **paralelismo** y **concurrencia** en Java, enfocado en la resolución de problemas de alto rendimiento computacional. En el laboratorio se abordan tres secciones fundamentales:

1. **Creación y manejo de hilos** en Java.
2. **Implementación de algoritmos paralelos** utilizando hilos.
3. **Evaluación del desempeño** del paralelismo en diferentes escenarios.

Se analiza el rendimiento de las soluciones desarrolladas mediante la variación del número de hilos utilizados y se compara con la ley de **Amdahl**, para evaluar los límites del paralelismo en un entorno controlado.

---

## Contenido

1. [Introducción](#introducción)
2. [Desarrollo del Laboratorio](#desarrollo-del-laboratorio)
   - [Parte I: Hilos Java](#parte-i-hilos-java)
   - [Parte II: Hilos Java](#parte-ii-hilos-java)
   - [Parte III: Evaluación de Desempeño](#parte-iii-evaluación-de-desempeño)
3. [Conclusiones](#conclusiones)

---

## Introducción

En este laboratorio se analizó el comportamiento y la optimización de hilos y paralelismo en Java, utilizando ejemplos prácticos para evaluar el desempeño de sistemas en diferentes configuraciones de hilos. A lo largo del laboratorio se observaron los límites del paralelismo, analizando el impacto del número de hilos en la eficiencia y el tiempo de ejecución. Además, se contrastaron los resultados con la ley de **Amdahl**, que describe el comportamiento teórico de los sistemas paralelizados.

---

## Desarrollo del Laboratorio

### Parte I: Hilos Java

1. **Creación de hilos:**
   Se creó una clase `CountThread` que extiende la clase `Thread`, y se diseñó para imprimir números dentro de un intervalo definido por los parámetros `A` y `B`. Se crearon tres hilos con intervalos de números diferentes y se ejecutaron utilizando el método `start()`.

2. **Cambio en la ejecución:**
   Al cambiar el uso de `start()` a `run()`, se observó que la ejecución ya no se realizaba de manera concurrente, sino secuencial, debido a que `run()` solo ejecuta el código en el hilo actual sin crear un nuevo hilo de ejecución.

#### Pregunta 1: ¿Cómo cambia la salida al cambiar el inicio con `start()` por `run()`? ¿Por qué?

**Respuesta:**  
Con el método `start()`, se crea un nuevo hilo en ejecución, y se llama al método `run()` dentro de ese nuevo hilo, permitiendo que la tarea se ejecute de forma paralela al hilo principal u otros hilos existentes.  
Por otro lado, al llamar directamente al método `run()`, no se crea un nuevo hilo, y la ejecución ocurre de manera secuencial en el hilo actual, como cualquier otro método común de Java.

---

### Parte II: Hilos Java

1. **Cálculo de dígitos de Pi:**
   Se desarrolló una clase `MiniPiDigits` que calculó una parte de los dígitos de Pi utilizando hilos. El cálculo se paralelizó dividiendo la tarea entre varios hilos.
   
2. **Método `join()`:**
   La función `PiDigits.getDigits()` fue modificada para aceptar un parámetro adicional `N`, que indica la cantidad de hilos a usar. El método `join()` se utilizó para esperar a que todos los hilos terminen antes de combinar los resultados y retornar el valor final.

3. **Pruebas de JUnit:**
   Se implementaron pruebas para validar el correcto funcionamiento del programa con 1, 2 y 3 hilos. Todas las pruebas pasaron exitosamente.

   ![image](https://github.com/user-attachments/assets/3583b609-b250-4fb3-b5bc-66b4bce233bc)


---

### Parte III: Evaluación de Desempeño

Se realizaron experimentos para evaluar el desempeño del cálculo de un millón de dígitos de Pi en diferentes configuraciones de hilos, comparando el tiempo de ejecución para los siguientes escenarios:

- 1 hilo
  
  ![image](https://github.com/user-attachments/assets/2cb377fb-149d-4fbd-af16-4001f6aa7262)
  ![image](https://github.com/user-attachments/assets/7fb08be9-fe0c-4e4e-a781-ab68752264d2)

- Número de hilos igual al número de núcleos del procesador.

  ![image](https://github.com/user-attachments/assets/be41f1e6-3570-4667-8780-341e62124d2a)
  ![image](https://github.com/user-attachments/assets/479b918e-0d70-4a48-979d-dd60266a9be6)

- El doble de hilos que núcleos del procesador.

![image](https://github.com/user-attachments/assets/7a601587-31c9-43e1-bb9c-7beeedf2021b)
![image](https://github.com/user-attachments/assets/a3f4ddcc-f29b-44e0-a36d-a4ef34d468fc)

- 200 hilos.

  ![image](https://github.com/user-attachments/assets/841e37f5-2360-40e3-a061-de37245ae0f5)
  ![image](https://github.com/user-attachments/assets/d063fe7a-ad2e-41fa-a821-02f2f15812f2)


- 500 hilos.

  ![image](https://github.com/user-attachments/assets/c462a45f-74e9-4b08-8344-0a60cb90b724)
![image](https://github.com/user-attachments/assets/0bddf2b7-bcf3-4c9b-a2d0-a3ebdfa8cba8)


#### Pregunta 2: ¿Cómo afecta el número de hilos al tiempo de ejecución en los experimentos realizados?

**Respuesta:**  
El aumento en el número de hilos reduce el tiempo de ejecución hasta cierto punto. Sin embargo, al usar más hilos de los que el hardware puede manejar, el rendimiento se ve afectado negativamente debido a la contención de recursos (como la CPU y la memoria). Por ejemplo, con 200 hilos, el tiempo de ejecución fue más bajo que con 500 hilos debido al mayor overhead y la competencia por los recursos.

#### Pregunta 3: Según la ley de Amdahl, ¿por qué el mejor desempeño no se logra con los 500 hilos? ¿Cómo se compara con el desempeño de 200 hilos?

**Respuesta:**  
La ley de Amdahl establece que el mejoramiento del rendimiento es limitado por la fracción del algoritmo que no puede paralelizarse. A pesar de tener más hilos (500), el tiempo de ejecución aumentó debido a la sobrecarga de administración de hilos y la competencia por los recursos.  
Con 200 hilos, el tiempo de ejecución fue mejor que con 500 hilos debido a que hubo menos overhead en la gestión de los hilos, lo que permitió un uso más eficiente de los recursos del sistema.

#### Pregunta 4: ¿Cómo se comporta la solución usando tantos hilos de procesamiento como núcleos comparados con el resultado de usar el doble de estos?

**Respuesta:**  
Usar tantos hilos como núcleos es más eficiente porque cada hilo puede ejecutarse en un núcleo diferente, minimizando el overhead y la contención de recursos. En cambio, usar el doble de hilos obliga al sistema operativo a realizar multiplexación entre hilos, lo que introduce overhead y reduce el rendimiento debido a la competencia por los recursos de la CPU y la memoria.

#### Pregunta 5: Si en lugar de 500 hilos en una sola CPU, se pudiera usar 1 hilo en cada una de 500 máquinas hipotéticas, ¿la ley de Amdahl se aplicaría mejor? Si en lugar de esto se usaran c hilos en 500/c máquinas distribuidas, ¿se mejoraría?

**Respuesta:**  
La ley de Amdahl sigue aplicándose de la misma manera, ya que el límite teórico depende de la fracción paralelizable del algoritmo (P). Sin embargo, al distribuir los hilos en varias máquinas, se reduce el overhead y la contención de recursos, lo que mejora el rendimiento práctico. Si se usan 500 hilos en 500 máquinas, la contención por recursos como la memoria RAM y la CPU sería menor, lo que resultaría en una mayor eficiencia en comparación con usar 500 hilos en una sola CPU.

---

## Conclusiones

- **Hilos y paralelismo:** El uso de hilos permite mejorar el rendimiento de los programas al dividir las tareas entre diferentes hilos de ejecución. Sin embargo, hay un límite práctico en la mejora, que depende de la contención de recursos como la CPU y la memoria RAM.
  
- **Ley de Amdahl:** Aunque la ley de Amdahl sugiere que un mayor número de hilos puede mejorar el desempeño, en la práctica, el overhead y la contención de recursos pueden degradar el rendimiento cuando el número de hilos es demasiado alto.

- **Optimización:** El mejor desempeño se logra cuando el número de hilos es igual o ligeramente superior al número de núcleos de CPU. Usar más hilos de los que el hardware puede manejar resulta en un mayor overhead y una disminución en el rendimiento.

- **Distribución de hilos en múltiples máquinas:** Distribuir los hilos en varias máquinas puede mejorar el rendimiento práctico al reducir la contención de recursos, aunque la ley de Amdahl sigue limitando la mejora teórica.

---

## Ejecución

Para ejecutar el laboratorio, clone el repositorio y compile el código usando los siguientes comandos:

```bash
git clone https://github.com/SebastianCardona-P/Lab01ARSW.git
cd <directorio-del-repositorio>

