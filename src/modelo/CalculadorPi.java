package modelo;

import java.util.Random;

public class CalculadorPi {

	private final int semilla;
	private final int puntosTotal;

	public CalculadorPi(int semilla, int puntosTotal) {
		super();//TODO: Es necesario?
		this.semilla = semilla;
		this.puntosTotal = puntosTotal;
	}
	
	public int calcularPuntosAdentro(){
		int puntosAdentro = 0;

		Random rng = new Random(semilla);
		float currentX = rng.nextFloat();
		float currentY = rng.nextFloat();
		float position = (currentX * currentX) + (currentY * currentY);

		for (int i = 0; i < puntosTotal; i++) {
			if (position <= 1) {
				puntosAdentro +=1;
			}
		}
		return puntosAdentro;
	}
}
