/*Dos jugadores se enfrentan en una guerra de dados: cada jugador tiene un dado de seis caras y diez fichas. Cree 
una clase JugadorGuerra, que tenga como atributos de instancia el número de fichas, un dado (una instancia de la 
clase Dado) y el nombre del jugador. Agregue dentro de esta clase el resto de elementos necesarios. Cree un 
algoritmo principal usando esta clase.
 */

import java.util.Random;
import java.util.Scanner;

public class JugadorGuerra {
    private String nombre;
    private int fichas;
    private Dado dado;

    public JugadorGuerra(String nombre, int fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
        this.dado = new Dado(6);
    }

    public String getNombre() {
        return nombre;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public int lanzarDado() {
        return dado.lanzar();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Nombre del jugador 1? ");
        String nombre1 = scanner.nextLine();
        System.out.print("¿Nombre del jugador 2? ");
        String nombre2 = scanner.nextLine();

        JugadorGuerra jugador1 = new JugadorGuerra(nombre1, 10);
        JugadorGuerra jugador2 = new JugadorGuerra(nombre2, 10);

        int marcador1 = 0;
        int marcador2 = 0;

        Random random = new Random();

        while (jugador1.getFichas() > 0 && jugador2.getFichas() > 0) {
            int resultado1 = jugador1.lanzarDado();
            int resultado2 = jugador2.lanzarDado();

            System.out.println(jugador1.getNombre() + " ha sacado un " + resultado1);
            System.out.println(jugador2.getNombre() + " ha sacado un " + resultado2);

            if (resultado1 > resultado2) {
                jugador2.setFichas(jugador2.getFichas() - 1);
                marcador1++;
                System.out.println(jugador1.getNombre() + " gana");
            } else if (resultado2 > resultado1) {
                jugador1.setFichas(jugador1.getFichas() - 1);
                marcador2++;
                System.out.println(jugador2.getNombre() + " gana");
            } else {
                System.out.println("Empate");
            }

            System.out.println("Marcador: " + jugador1.getNombre() + " " + marcador1 + " - " + jugador2.getNombre() + " " + marcador2);
            System.out.println();
        }

        if (jugador1.getFichas() == 0) {
            System.out.println(jugador2.getNombre() + " ha ganado");
        } else {
            System.out.println(jugador1.getNombre() + " ha ganado");
        }
    }
}

class Dado {
    private int numCaras;
    private Random random;

    public Dado(int numCaras) {
        this.numCaras = numCaras;
        this.random = new Random();
    }

    public int lanzar() {
        return random.nextInt(numCaras) + 1;
    }
}
