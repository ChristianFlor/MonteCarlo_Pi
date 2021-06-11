package controladorMC;

import org.osoa.sca.annotations.*;
import servicios.*;
import repositorios.*;
import modelo.*;
import java.util.*;
import java.io.*;
import java.util.Map.Entry;
import javax.swing.JFrame;
import java.awt.event.*;
import interfazUsuario.Interfaz;



@Scope("COMPOSITE")
public class ControladorMC implements Runnable, ServicioMC{
    @Reference
	private ServicioMC servicioMC;

    //@Reference
    private TestRepositorio test = TestRepositorio.getInstance();
    //@Reference
    private OutputRepositorio output = OutputRepositorio.getInstance();

	private List<Test> tests = new ArrayList<Test>();

    private Interfaz frame;
    private int codMaquina;
    private double suma;

    public void run() {

		try {
			frame = new Interfaz();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		arrancarMaquina();
		eventos();
	}

	public void 

	@Override
	public double calcularPi(int seed, int points){
		CalculadorPi calculador = new CalculadorPi(seed, points);
		int puntosAdentro = calculador.calcularPuntosAdentro();
		double pi = puntosAdentro/points;
		return pi;
	}
/*

	@Override
    public void generarPuntos(){

	}**/

	
    private void lecturaPruebas() {

		FileInputStream fstream;
		try {
			String path = "../../data/test.txt";
			File file = new File(path);
			fstream = new FileInputStream(file);
			DataInputStream entrada = new DataInputStream(fstream);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String line = buffer.readLine();
			while(!line.isEmpty()){
				String nums[] = line.split("-");
				int points = Integer.parseInt(nums[0]);
				int seed = Integer.parseInt(nums[1]);
				Test test =  new Test(nums[1], seed, points);
				tests.add(test);
				line = buffer.readLine();
			}
			entrada.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void escribirResultados(String output){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("../../data/resultados.csv");
            pw = new PrintWriter(fichero);
        	pw.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}

	public void initializeModel(){
		String encabezado = "Valor Pi;Tiempo de Ejecución\n";
		String output = "";
		for(int i = 0; i < tests.size(); i++){
			output += "Test "+(i+1)+"\n"+encabezado;
			int totalPuntos = tests.get(i).getPoints();
			int seed = tests.get(i).getSeed();
			long average = 0;
			for(int j = 0; j<10; j++){
				long timeNow = System.currentTimeMillis();
				calcularPi(seed, totalPuntos);
				long timeAfter = System.currentTimeMillis();
				long totalTime = timeAfter - timeNow;
				
				average += totalTime;
				output += pi+";"+totalTime+"\n";
			}
			average = average/10;
			output += "Promedio;"+average+"\n";
			output += "Nodos;1\n\n";
		}
		escribirResultados(output);
	}

}
