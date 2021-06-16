Integrantes:
Christian David Flor Astudillo
Gabriel Cruz Libreros
Natalia Isabel Gonzalez Murillo


Este proyecto está soportado en cualquier sistema operativo.

Aunque el proyecto está implementado en varios componentes SCA independientes y desplegables en cualquier máquina, en la configuración actual todos ellos están configurados para ser desplegados en la misma máquina (nodo de procesamiento).

Para montar el proyecto, se debe tener configurado FraSCAti y se deben seguir los siguientes pasos:

1)Estando un nivel arriba de MonteCarlo_Pi, compilar los proyectos: 	

frascati compile MonteCarlo_Pi/src MonteCarlo_Pi

2) Correr los proyectos en el siguiente orden y con su respectivo archivo composite:

frascati run MonteCarlo_Pi -libpath MonteCarlo_Pi.jar -s r -m run
