import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca un numero");
        int serie = Integer.parseInt(scan.nextLine());
        System.out.println("¿Desea ver la serie completa o el numero que ha introducido?");
        System.out.println("OPCIONES: \n'Serie Completa' \n'Numero Introducido'");
        String eleccion = scan.nextLine().toLowerCase();
        if (eleccion.equals("serie completa")) {
            for (int i = 1; i <= serie; i++) {
                if (i < serie) {
                    System.out.print(fibonacciRecursivo(i) + ", ");
                } else {
                    System.out.print(fibonacciRecursivo(i) + ".");
                }
            }

        } else if (eleccion.equals("numero introducido")) {
            System.out.println(fibonacciRecursivo(serie));

        } else {
            System.out.println("Ha introducido un valor erroneo");
        }
    }


    /**
     * Devuelve el numero fibonacci dado su posicion
     *
     * @param n Posicion del numero fibonacci
     * @return Numero de la lista de fibonacci
     */
    public static int fibonacciRecursivo(int n) {

        //CASO BASE, si es cero o menor que cero devuelve un cero
        if (n <= 0) {
            return 0;
            //CASO UNIDAD, si es 1 devuelve un 1
        } else if (n == 1) {
            return 1;
        } else {
            //Hago la suma
            return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
        }

    }

}

