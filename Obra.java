/*Los clientes creados en el capítulo anterior, pueden comprar libros y vídeos a nuestro editor. Todos 
los libros y vídeos tienen un título y un autor. Un libro tiene varias páginas, mientras que un vídeo 
tiene una duración. Crear una clase Obra, agrupando los elementos comunes a libros y vídeos y dos clases
 Libros y Video, herederas de esta, con los elementos específicos para cada una. Luego cree una clase 
 Pedido, asociando a un cliente un conjunto de obras compradas. */

import java.util.ArrayList;
import java.util.Scanner;

// Clase Obra que agrupa los elementos comunes de los libros y vídeos
class Obra {
    protected String titulo;
    protected String autor;

    public Obra(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
}

// Clase Libro que hereda de Obra e incluye el número de páginas
class Libro extends Obra {
    private int numPaginas;

    public Libro(String titulo, String autor, int numPaginas) {
        super(titulo, autor);
        this.numPaginas = numPaginas;
    }

    public String toString() {
        return "Libro " + titulo + " de " + autor + " (" + numPaginas + " páginas)";
    }
}

// Clase Video que hereda de Obra e incluye la duración en minutos
class Video extends Obra {
    private int duracion;

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public String toString() {
        int horas = duracion / 60;
        int minutos = duracion % 60;
        return "Vídeo " + titulo + " de " + autor + " (" + horas + "h" + minutos + ")";
    }
}

// Clase Cliente que almacena el nombre y los pedidos realizados
class Cliente {
    private String nombre;
    private ArrayList<Obra> pedidos;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPedido(Obra obra) {
        pedidos.add(obra);
    }

    public String toString() {
        return "Cliente " + nombre;
    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + ":");
        for (Obra obra : pedidos) {
            System.out.println(" - " + obra);
        }
    }
}

// Clase Pedido que asocia un cliente con un conjunto de obras compradas
class Pedido {
    private Cliente cliente;
    private ArrayList<Obra> obras;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.obras = new ArrayList<>();
    }

    public void agregarObra(Obra obra) {
        obras.add(obra);
    }

    public void mostrarPedido() {
        System.out.println("* Pedido de: " + cliente.getNombre() + ":");
        for (Obra obra : obras) {
            System.out.println("   - " + obra);
        }
        System.out.println();
    }
}
 class TiendaEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Obra> obras = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        int opcion;

        do {
            System.out.println("*-*-* Menú *-*-*\n");
            System.out.println("1 - Mostrar clientes");
            System.out.println("2 - Crear cliente");
            System.out.println("3 - Crear una obra");
            System.out.println("4 - Crear un pedido");
            System.out.println("5 - Mostrar pedidos");
            System.out.println("6 - Salir");
            System.out.print("\nIngrese una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("-- Clientes --");
                    for (Cliente cliente : clientes) {
                        System.out.println(cliente);
                    }
                    break;

                case 2:
                    sc.nextLine(); // Limpiar buffer de entrada
                    System.out.print("¿Nombre del cliente? ");
                    String nombreCliente = sc.nextLine();
                    Cliente nuevoCliente = new Cliente(nombreCliente);
                    clientes.add(nuevoCliente);
                    System.out.println("Cliente " + nombreCliente + " añadido");
                    break;

                case 3:
                    sc.nextLine(); // Limpiar buffer de entrada
                    System.out.print("¿Título de la obra? ");
                    String tituloObra = sc.nextLine();
                    System.out.print("¿Autor de la obra? ");
                    String autorObra = sc.nextLine();
                    System.out.print("¿Es un libro (L) o un vídeo (V)? ");
                    String tipoObra = sc.nextLine();
                    if (tipoObra.equals("L")) {
                        System.out.print("¿Número de páginas? ");
                        int numPaginas = sc.nextInt();
                        Libro nuevoLibro = new Libro(tituloObra, autorObra, numPaginas);
                        obras.add(nuevoLibro);
                    } else if (tipoObra.equals("V")) {
                        System.out.print("¿Duración en minutos? ");
                        int duracion = sc.nextInt();
                        Video nuevoVideo = new Video(tituloObra, autorObra, duracion);
                        obras.add(nuevoVideo);
                    } else {
                        System.out.println("Opción no válida");
                    }
                    break;

                case 4:
                    sc.nextLine(); // Limpiar buffer de entrada
                    System.out.print("¿Nombre del cliente? ");
                    String nombreClientePedido = sc.nextLine();
                    Cliente clientePedido = null;
                    for (Cliente cliente : clientes) {
                        if (cliente.getNombre().equals(nombreClientePedido)) {
                            clientePedido = cliente;
                            break;
                        }
                    }
                    if (clientePedido == null) {
                        System.out.println("Cliente no encontrado");
                        break;
                    }
                    Pedido nuevoPedido = new Pedido(clientePedido);
                    pedidos.add(nuevoPedido);
                    System.out.println("Pedido para " + nombreClientePedido + " creado");
                    String opcionPedido;
                    do {
                        System.out.print("¿Añadir obra (S/N)? ");
                        opcionPedido = sc.nextLine();
                        if (opcionPedido.equals("S")) {
                            System.out.print("¿Título de la obra? ");
                            String tituloObraPedido = sc.nextLine();
                            Obra obraPedido = null;
                            for (Obra obra : obras) {
                                if (obra.titulo.equals(tituloObraPedido)) {
                                    obraPedido = obra;
                                    break;
                                }
                            }
                            if (obraPedido == null) {
                                System.out.println("Obra no encontrada");
                                break;
                            }
                            nuevoPedido.agregarObra(obraPedido);
                            clientePedido.agregarPedido(obraPedido);
                            System.out.println("Obra " + tituloObraPedido + " añadida al pedido");
                        }
                    } while (opcionPedido.equals("S"));
                    break;

                case 5:
                    System.out.println("-- Pedidos --");
                    for (Pedido pedido : pedidos) {
                        pedido.mostrarPedido();
                    }
                    break;
            }
        } while (opcion != 6);
    }
}

