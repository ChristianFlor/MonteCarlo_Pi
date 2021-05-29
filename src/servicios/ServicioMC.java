package servicios;

import org.osoa.sca.annotations.Service;

@Service
public interface ServicioMC{
	
	public void calcularPi(int seed, int points);

    public void generarPuntos();

}
