
import controllers.SortPersonaMethods;
import models.Persona;
import models.Resultado;
import utils.Benchmarking;
import java.util.concurrent.Callable;

public class App {
    public static void main(String[] args) {
        SortPersonaMethods metodos = new SortPersonaMethods();
        int[] tamanos = {10000, 50000, 100000};

        for (int size : tamanos) {

            Persona[] base = generarPersonas(size);
            Persona[] copiaInsercion = base.clone();
            Persona[] copiaQuick = base.clone();

            Callable<Void> insertion = () -> {
                metodos.insertionSort(copiaInsercion);
                return null;
            };
            Callable<Void> quick = () -> {
                metodos.quickSort(copiaQuick, 0, copiaQuick.length - 1);
                return null;
            };

            Resultado r1 = Benchmarking.medirTiempo(insertion, "Inserción", "Desordenado", copiaInsercion.length);
            Resultado r2 = Benchmarking.medirTiempo(quick, "QuickSort", "Desordenado", copiaQuick.length);

            System.out.println(r1);
            System.out.println(r2);

            Persona[] base2 = generarPersonas(size);
            metodos.quickSort(base2, 0, base2.length - 1);

            Persona[] nuevoArreglo = new Persona[base2.length + 1];
            System.arraycopy(base2, 0, nuevoArreglo, 0, base2.length);
            nuevoArreglo[nuevoArreglo.length - 1] = new Persona("PersonaExtra", (int)(Math.random() * 101));

            Persona[] copiaInsercion2 = nuevoArreglo.clone();
            Persona[] copiaQuick2 = nuevoArreglo.clone();

            Callable<Void> insertion2 = () -> {
                metodos.insertionSort(copiaInsercion2);
                return null;
            };
            Callable<Void> quick2 = () -> {
                metodos.quickSort(copiaQuick2, 0, copiaQuick2.length - 1);
                return null;
            };

            Resultado r3 = Benchmarking.medirTiempo(insertion2, "Inserción", "Casi ordenado + 1 persona", copiaInsercion2.length);
            Resultado r4 = Benchmarking.medirTiempo(quick2, "QuickSort", "Casi ordenado + 1 persona", copiaQuick2.length);

            System.out.println(r3);
            System.out.println(r4);
        }
    }

    public static Persona[] generarPersonas(int cantidad) {
        Persona[] personas = new Persona[cantidad];
        for (int i = 0; i < cantidad; i++) {
            String nombre = "Persona " + (i + 1);
            int edad = (int) (Math.random() * 101);
            personas[i] = new Persona(nombre, edad);
        }
        return personas;
    }
}




      
