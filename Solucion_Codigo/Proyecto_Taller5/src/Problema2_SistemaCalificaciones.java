/**
 * Desarrolla un programa para gestionar las calificaciones de los estudiantes en una materia. El programa debe tener dos clases principales: "Estudiante" y "Materia".
 * Del Estudiante se registra su: nombre, edad y la materia a cruzar.
 * Esta clase debe verificar la aprobación dadas tres calificaciones de una materia: ACD (3.5/10), APE (3.5/10) y AA (3/10). Se aprueba si la sumatoria es de al menos 
 * 70%, si cumple con este requisito, se considera que ha aprobado, caso contrario informar al estudiante que deberá rendir un examen de recuperación sobre 3.5/10 pts. 
 * agregado al 60% acumulado de los componentes ACD, APE y AA.
 * Ahora, debe implementar los siguientes requisitos en su programa:
 * El programa debe permitir al usuario ingresar los datos de un estudiante, incluyendo su nombre y edad.
 * El programa debe permitir al usuario ingresar los datos de una materia, incluyendo su nombre y las calificaciones del estudiante en las categorías ACD, APE y AA.
 * El programa debe permitir al usuario vincular una materia a un estudiante, es decir, agregar la materia al estudiante.
 * El programa debe verificar si un estudiante ha aprobado una materia específica. Para ello, se deben evaluar las calificaciones del estudiante en las tres categorías 
 * (ACD, APE y AA).
 * @author Luis
 */
import java.util.Scanner;
public class Problema2_SistemaCalificaciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese la edad del estudiante: ");
        int edad = sc.nextInt();
        Estudiante estudiante = new Estudiante(nombre, edad);
        sc.nextLine(); // limpiar buffer

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreMateria = sc.nextLine();
        System.out.print("Ingrese la nota ACD (sobre 3.5): ");
        double acd = sc.nextDouble();
        System.out.print("Ingrese la nota APE (sobre 3.5): ");
        double ape = sc.nextDouble();
        System.out.print("Ingrese la nota AA (sobre 3.0): ");
        double aa = sc.nextDouble();

        Materia materia = new Materia(nombreMateria, acd, ape, aa);
        estudiante.asignarMateria(materia);
        estudiante.verificarAprobacion();
    }
}
class Estudiante {
    public String nombre;
    public int edad;
    public Materia materia;

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public void asignarMateria(Materia materia) {
        this.materia = materia;
    }
    public void verificarAprobacion() {
        if (materia == null) {
            System.out.println("El estudiante no tiene materia asignada.");
            return;
        }
        System.out.println("Estudiante: " + nombre + " - Edad: " + edad);
        System.out.println(materia);

        if (materia.determinarAprobado()) {
            System.out.println("Resultado: ¡Aprobado!");
        } else {
            System.out.println("Resultado: No aprobado.");
            System.out.println("Debe rendir examen de recuperación (mínimo 3.5/10).");
            double notaRec = materia.notaRecuperacion(3.5);
            System.out.println("Nota con recuperación: " + notaRec + "/10.0");
        }
    }
}
class Materia {
    public String nombre;
    public double acd; // 3.5 pts
    public double ape; // 3.5 pts
    public double aa;  // 3.0 pts

    public Materia(String nombre, double acd, double ape, double aa) {
        this.nombre = nombre;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }
    public double calcularNotaTotal() {
        return acd + ape + aa;
    }
    public boolean determinarAprobado() {
        double total = calcularNotaTotal();
        return (total / 10.0) >= 0.7;
    }
    public double notaRecuperacion(double examenRecuperacion) {
        double parcial = calcularNotaTotal() * 0.6;
        return parcial + examenRecuperacion;
    }
    @Override
    public String toString() {
        return "Materia: " + nombre +
                "\nACD: " + acd + "/3.5" +
                "\nAPE: " + ape + "/3.5" +
                "\nAA: " + aa + "/3.0" +
                "\nNota Total: " + calcularNotaTotal() + "/10.0";
    }
}