import model.ConexionSingleton;
import view.MenuPrincipal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Comprueba la conexion a la base de datos
        ConexionSingleton conn = ConexionSingleton.getInstance();

        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.mostrarMenu();
    }
}