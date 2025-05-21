
import java.util.ArrayList;
import java.util.Scanner;

public class Problema4_AppFiscalia {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char opcion = 'S';
        // Arrays predefinidos para datos aleatorizados
        String[] nombresPersonas = {"Juan Cueva", "Ana Gómez", "Dana Ochoa", "Carmen Gutierrez", "Ismael Sánchez"};
        String[] ocupaciones = {"Funcionario público", "Contador", "Abogado", "Empresario", "Juez"};
        String[] roles = {"Acusado", "Testigo", "Víctima", "Abogado defensor"};

        while (opcion == 'S' || opcion == 's') {
            System.out.println("\n=== NUEVO CASO DE CORRUPCIÓN ===");

            // Creabcion del caso
            String[] fechaInicio = {
                String.valueOf(2023 + (int) (Math.random() * 3)), // Año aleatorio (2023-2025)
                String.valueOf(1 + (int) (Math.random() * 12)), // Mes aleatorio (1-12)
                String.valueOf(1 + (int) (Math.random() * 28)) // Día aleatorio (1-28)
            };
            CasoCorrupcion caso = new CasoCorrupcion(
                    "Caso " + (char) (65 + (int) (Math.random() * 26)) + "-" + (1000 + (int) (Math.random() * 9000)),
                    fechaInicio, "Presunto delito de " + new String[]{"sobornos", "malversación", "tráfico de influencias"}[(int) (Math.random() * 3)]);

            // Agregar personas implicadas (entre 1 y 4)
            int numPersonas = 1 + (int) (Math.random() * 4);
            ArrayList<PersonaImplicada> personas = new ArrayList<>();

            for (int i = 0; i < numPersonas; i++) {
                personas.add(new PersonaImplicada(
                        nombresPersonas[(int) (Math.random() * nombresPersonas.length)],
                        25 + (int) (Math.random() * 40), // Edad aleatoria (25-65)
                        ocupaciones[(int) (Math.random() * ocupaciones.length)],
                        roles[(int) (Math.random() * roles.length)],
                        (int) (Math.random() * 2), // 0 = no colabora, 1 = colabora
                        Math.random() * 5, // Sentencia aleatoria (0-5 años)
                        10000 + (Math.random() * 90000) // Daño económico ($10,000-$100,000)
                ));
            }
            caso.personas = personas;
            //Procesar y mostrar resultados
            caso.actualizarEstado();
            caso.mostrarInfo();
            System.out.print("\n¿Desea crear otro caso? (S/N): ");
            opcion = sc.next().charAt(0);
        }
        System.out.println("\n=== PROGRAMA FINALIZADO ===");
    }
}

class CasoCorrupcion {

    String nombre;
    String[] fechaInicio; // Formato: {"año", "mes", "día"}
    String estado;
    String detalles;
    ArrayList<PersonaImplicada> personas;

    public CasoCorrupcion(String nombre, String[] fechaInicio, String detalles) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.detalles = detalles;
        this.estado = "Iniciado";
        this.personas = new ArrayList<>();
    }

    public void actualizarEstado() {
        // Simulación de días transcurridos (aleatorio para prueba)
        int diasTranscurridos = (int) (Math.random() * 30); // 0-29 días

        if (diasTranscurridos > 14) {
            estado = "URGENTE (más de 2 semanas)";
        } else if (diasTranscurridos > 7) {
            estado = "Alerta (más de 1 semana)";
        }
    }

    public double calcularFianza(PersonaImplicada persona) {
        if (persona.nivelImplicacion.equals("Acusado") && persona.colabora == 1 && persona.sentencia < 1) {
            return persona.danioEconomico * 0.5;
        }
        return 0;
    }

    public void mostrarInfo() {
        System.out.println("\n--- DATOS DEL CASO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha inicio: " + fechaInicio[2] + "/" + fechaInicio[1] + "/" + fechaInicio[0]);
        System.out.println("Estado: " + estado);
        System.out.println("Detalles: " + detalles);

        System.out.println("\n--- PERSONAS IMPLICADAS ---");
        for (PersonaImplicada persona : personas) {
            System.out.println("Nombre: " + persona.nombre);
            System.out.println("  Edad: " + persona.edad + " años");
            System.out.println("  Ocupación: " + persona.ocupacion);
            System.out.println("  Rol: " + persona.nivelImplicacion);
            System.out.println("  Colabora: " + (persona.colabora == 1 ? "Sí" : "No"));

            if (persona.nivelImplicacion.equals("Acusado")) {
                System.out.println("  Sentencia: " + String.format("%.1f", persona.sentencia) + " años");
                System.out.println("  Daño económico: $" + String.format("%,.2f", persona.danioEconomico));
                double fianza = calcularFianza(persona);
                if (fianza > 0) {
                    System.out.println("  [FIANZA CALCULADA]: $" + String.format("%,.2f", fianza));
                }
            }
            System.out.println("----------------------");
        }
    }

    @Override
    public String toString() {
        return "CasoCorrupcion{"
                + "\nNombre:" + nombre
                + "\nFecha inicio: " + fechaInicio
                + "\nEstado: " + estado
                + "\nDetalles: " + detalles
                + "\nPersonas: " + personas
                + "\n}\n";
    }

}

class PersonaImplicada {

    String nombre;
    int edad;
    String ocupacion;
    String nivelImplicacion;
    int colabora; // 0 = No, 1 = Sí
    double sentencia; // en años
    double danioEconomico;

    public PersonaImplicada(String nombre, int edad, String ocupacion,
            String nivelImplicacion, int colabora,
            double sentencia, double danioEconomico) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.colabora = colabora;
        this.sentencia = sentencia;
        this.danioEconomico = danioEconomico;
    }

    @Override
    public String toString() {
        return "PersonaImplicada{"
                + "\nNombre: " + nombre
                + "\nEdad: " + edad
                + "\nOcupacion: " + ocupacion
                + "\nNivel implicacion: " + nivelImplicacion
                + "\nColabora: " + colabora
                + "\nSentencia: " + sentencia
                + "\nDaño economico: " + danioEconomico
                + "\n}\n";
    }

}
