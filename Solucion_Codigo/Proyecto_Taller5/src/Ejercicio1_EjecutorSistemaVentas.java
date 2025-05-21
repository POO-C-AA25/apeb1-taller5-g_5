import java.util.ArrayList;

public class Ejercicio1_EjecutorSistemaVentas {

    public static void main(String[] args) {

        //Creación de la lista de productos
        ArrayList<Producto> listaProducto = new ArrayList<>();

        //Se agregan productos directamente
        listaProducto.add(new Producto("Leche", 0.99, 3));
        listaProducto.add(new Producto("Pan", 1.20, 2));
        listaProducto.add(new Producto("Huevos", 2.50, 1));

        //Se imprimen los productos
        for (Producto producto1 : listaProducto) {
            System.out.print(producto1.toString());
        }

        // Simulación de la compra
        CarritoDeCompras carrito = new CarritoDeCompras(listaProducto); // Carrito con varios productos
        carrito.montoCliente = 10.00; // Monto del cliente

        System.out.println("\n=== Detalle de Compra ===");
        carrito.calcularTotal();
        carrito.realizarPago();
        System.out.println();
        carrito.mostrarDetalleCompra();

        System.out.println("\n");
    }
}

//Declaración de la clase producto
class Producto {

    public String nombre;
    public double precio;
    public int cantidad;

    //Constructores
    public Producto() {
    }

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nPrecio: " + precio
                + "\nCantidad: " + cantidad
                + "\n";
    }

}

//Declaración de la clase Carrito de compras
class CarritoDeCompras {

    public ArrayList<Producto> listaProductos;
    public double montoCliente;
    public double totalPagar;

    //Constructores
    public CarritoDeCompras() {
        this.listaProductos = new ArrayList<>();
    }

    public CarritoDeCompras(ArrayList<Producto> productos) {
        this.listaProductos = productos;
    }

    //Métodos
    public double calcularTotal() {
        totalPagar = 0;
        for (Producto producto : listaProductos) {
            totalPagar += producto.precio * producto.cantidad;
        }
        return totalPagar;
    }

    public void realizarPago() {
        if (montoCliente > totalPagar) {
            System.out.print("Pago exitoso.");
            System.out.print("\nSobrante: " + (montoCliente - totalPagar));
        } else if (montoCliente < totalPagar) {
            System.out.print("Pago insuficiente.");
            System.out.print("\nFaltante: " + (totalPagar - montoCliente));
        } else {
            System.out.print("Pago exacto realizado.");
            System.out.print("\nSobrante: 0.0");
        }
    }

    public void mostrarDetalleCompra() {
        System.out.println("\n\n=== Productos Comprados ===");
        for (Producto producto : listaProductos) {
            System.out.print(producto.toString());
        }

        System.out.println("Total a pagar: " + totalPagar);
        System.out.println("Total de efectivo recibido: " + montoCliente);
    }
}
