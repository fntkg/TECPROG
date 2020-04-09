class Main {

	public static void main(String[] args) {
		Camion camion = new Camion(10);

		Contenedor<Generico> contenedor_generico = new Contenedor<>(4);
		Contenedor<Toxico>   contenedor_toxico   = new Contenedor<>(3);
		Contenedor<SerVivo>  contenedor_servivo  = new Contenedor<>(2);
		Producto             apuntes_tepro       = new Producto(1.0,0.1,"Apuntes de Tecnologia de Programacion");

		Producto longaniza_graus  = new Producto(0.5,0.8,"Longaniza de Graus");
		contenedor_generico.guardar(longaniza_graus);

		Toxico discos_melendi = new Toxico(1,20,"Discos de Melendi");
		//contenedor_generico.guardar(discos_melendi); //Esto no deberia compilar
		contenedor_toxico.guardar(discos_melendi);

		SerVivo elvis_presley = new SerVivo(0.1,100,"Elvis Presley");
		//camion.guardar(elvis_presley); //Esto no deberia compilar
		contenedor_servivo.guardar(elvis_presley);

		if (!camion.guardar(contenedor_generico)) System.out.println("Camion lleno con contenedor generico");
		if (!camion.guardar(contenedor_toxico))   System.out.println("Camion lleno con contenedor toxicos");
		if (!camion.guardar(contenedor_servivo))  System.out.println("Camion lleno con contenedor de seres vivos");
		if (!camion.guardar(apuntes_tepro))   System.out.println("Camion lleno con apuntes de tepro");

		Producto trenzas_almudevar = new Producto(0.5,0.8,"Trenzas de Almudevar");
		if (!camion.guardar(trenzas_almudevar))   System.out.println("Camion lleno con trenzas de Almudevar");

		System.out.println(camion); //Automaticamente llama a toString()
		/* La salida del programa hasta aquí deberia ser parecida a lo siguiente

		Camion lleno con trenzas de Almudevar
		Camion [10 m3] [120.9 kg]
		   Contenedor de genéricos [4 m3] [0.8 kg]
			  Longaniza de Graus [0.5 m3] [0.8 kg]
		   Contenedor de productos tóxicos [3 m3] [20 kg]
			  Discos de Melendi [1 m3] [20 kg]
		   Contenedor de seres vivos [2 m3] [100 kg]
			  Elvis Presley [0.1 m3] [100 kg]
		   Apuntes de Tecnologia de Programacion [1 m3] [0.1 kg]
		*/

		Contenedor<Generico> otro_contenedor_generico = new Contenedor<>(1);
		Contenedor<Toxico>   otro_contenedor_toxico   = new Contenedor<>(1);
		Contenedor<SerVivo>  otro_contenedor_servivo  = new Contenedor<>(1);
		contenedor_generico.guardar(otro_contenedor_generico);
		contenedor_generico.guardar(otro_contenedor_toxico);
		contenedor_generico.guardar(otro_contenedor_servivo);
		//contenedor_generico.guardar(camion); //Esto no deberia compilar
		//contenedor_toxico.guardar(otro_contenedor_toxico); //Esto no deberia compilar
		//contenedor_servivo.guardar(otro_contenedor_servivo); //Esto no deberia compilar



	}

}
