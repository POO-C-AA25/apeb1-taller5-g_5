
import java.util.ArrayList;

public class Ejercicio3_EjecutorEmpresa {

    public static void main(String[] args) {
        ArrayList<Departamento> listaDepartamentos = new ArrayList<>();
        Empresa empresaTemp = new Empresa();
        Departamento departamentoTemp = new Departamento();

        //Se agregan los datos de los departamentos
        departamentoTemp.ingresarDepartamento("Ventas", 12, 525000, listaDepartamentos);
        departamentoTemp.ingresarDepartamento("RRHH", 30, 600000, listaDepartamentos);
        departamentoTemp.ingresarDepartamento("Administrativo", 10, 1000000, listaDepartamentos);
        
        //Se invoca el metodo para verificar la categoria
        
        empresaTemp.comprobarCategoria(listaDepartamentos);
        
        //Se imprime la informacion de los departamentos
        
        departamentoTemp.imprimirDepartamentos(listaDepartamentos);
    }
}

class Empresa {

    public String nombre;
    public int RUC;
    public String direccionAsignada;

    //Constructores 
    public Empresa() {
    }

    public Empresa(String nombre, int RUC, String direccionAsignada) {
        this.nombre = nombre;
        this.RUC = RUC;
        this.direccionAsignada = direccionAsignada;
    }

    // Metodo que permite a la empresa gestionar la categoria de los departamentos
    public void comprobarCategoria(ArrayList<Departamento> listaDepartamentos) {
        //Se comprueba la categoria en toda la lista de departamentos
        for (Departamento departamento1 : listaDepartamentos) {
            if (departamento1.numeroEmpleados > 20 && departamento1.produccionAnual > 1000000) {
                departamento1.categoriaMerecida = 'A';
            } else if (departamento1.numeroEmpleados >= 20 && departamento1.produccionAnual == 1000000) {
                departamento1.categoriaMerecida = 'B';
            } else if (departamento1.numeroEmpleados >= 10 && departamento1.produccionAnual >= 500000) {
                departamento1.categoriaMerecida = 'C';
            } else {
                departamento1.categoriaMerecida = 'D'; //Categoria por defecto en caso no se cumplan las condiciones
            }
        }

    }

    // metodo toString
    @Override
    public String toString() {
        return "Empresa{"
                + "\nNombre: " + nombre
                + "\nRUC: " + RUC
                + "\nDireccion asignada: " + direccionAsignada
                + "\n}\n";
    }
}

class Departamento {

    public String nombre;
    public int numeroEmpleados;
    public double produccionAnual;
    public char categoriaMerecida = 'D'; //Se incializa puesto que tenemos la categoria D por defecto

    //Constructores
    public Departamento() {
    }

    public Departamento(String nombre, int numeroEmpleados, double produccionAnual, char categoriaMerecida) {
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
        this.produccionAnual = produccionAnual;
        this.categoriaMerecida = categoriaMerecida;
    }

    //Metodo para ingresar los departamentos en una lista
    public void ingresarDepartamento(String nombre, int numeroEmpleados, double produccionAnual, ArrayList<Departamento> listaDepartamentos) {
        listaDepartamentos.add(new Departamento(nombre, numeroEmpleados, produccionAnual, categoriaMerecida));
    }

    //Metodo para imprimir la informacion de los departamentos
    public void imprimirDepartamentos(ArrayList<Departamento> listaDepartamentos) {
        for (Departamento listaDepartamento : listaDepartamentos) {
            System.out.println(listaDepartamento.toString());
        }
    }
    //metodo toString

    @Override
    public String toString() {
        return "Departamento{"
                + "\nNombre:" + nombre
                + "\nNumero empleados:" + numeroEmpleados
                + "\nProduccion anual:" + produccionAnual
                + "\nCategoria merecida:" + categoriaMerecida
                + "\n}\n";
    }

}
