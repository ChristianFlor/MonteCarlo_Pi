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

   /* @Reference
	private ServicioMC servicioMC;*/

    //@Reference
    private TestRepositorio test = TestRepositorio.getInstance();
    //@Reference
    private OutputRepositorio output = OutputRepositorio.getInstance();

	private List<Test> tests = new ArrayList<Test>();

    private Interfaz frame;
    private int codMaquina;
    private double suma;

	public final static String COMA = ",";

    public void run() {
		System.out.println("Run");
		try {
			frame = new Interfaz();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lecturaPruebas();
		initializeModel();
	}


	@Override
	public double calcularPi(int seed, long points){
		CalculadorPi calculador = new CalculadorPi(seed, points);
		long puntosAdentro = calculador.calcularPuntosAdentro();
		double pi = puntosAdentro*4/points;
		return pi;
	}
/*

	@Override
    public void generarPuntos(){

	}**/

	
    private void lecturaPruebas() {
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fstream;
		try {
			String path = "MonteCarlo_Pi/data/test.txt";
			File file = new File(path);
			fstream = new FileInputStream(file);
			DataInputStream entrada = new DataInputStream(fstream);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String line = buffer.readLine();
			while(line!=null && !line.isEmpty()){
				String nums[] = line.split("-");
				long points = Long.parseLong(nums[0]);
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
		System.out.println("Escribiendo");
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("MonteCarlo_Pi/data/resultados.csv");
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
		System.out.println("Finish");
	}

	public void initializeModel(){
		
		String encabezado = "Valor Pi"+COMA+"Tiempo de Ejecucion\n";
		String output = "";
		for(int i = 0; i < tests.size(); i++){
			output += "Test "+(i+1)+"\n"+encabezado;
			long totalPuntos = tests.get(i).getPoints();
			int seed = tests.get(i).getSeed();
			long average = 0;
			for(int j = 0; j<10; j++){
				long timeNow = System.currentTimeMillis();
				double pi = calcularPi(seed, totalPuntos);
				long timeAfter = System.currentTimeMillis();
				long totalTime = timeAfter - timeNow;
				
				average += totalTime;
				output += pi+COMA+totalTime+"\n";
			}
			average = average/10;
			output += "Promedio"+COMA+average+"\n";
			output += "Nodos"+COMA+"1\n\n";
		}
		escribirResultados(output);
	}

	public void showValues(){
		
	}

}
