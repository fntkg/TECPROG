import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 PARA LEER UN STRING PATH Y PASAR CADA ELEMENTO  A UNA LISTA
        List<String> new_path;
        new_path = Arrays.asList(path.split("/"));
 */

class Main
{
	public static void main(String[] args) {
		Directorio raiz;
		Ruta ruta;
		try {
			raiz = new Directorio("");
			ruta = new Ruta(raiz);
		} catch (ExcepcionArbolFicheros e) { return; }

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (boolean end=false; !end;)
		{
			System.out.print(ruta.pwd()+"> ");
			try
			{
				String[] argv = br.readLine().split(" ");
				if (argv[0].equals("pwd"))
				{
					System.out.println(ruta.pwd());
				}
				else if (argv[0].equals("ls"))
				{
					System.out.println(ruta.ls());
				}
				else if (argv[0].equals("du"))
				{
					System.out.println(ruta.du());
				}
				else if (argv[0].equals("cd"))
				{
					ruta.cd(argv[1]);
				}
				else if (argv[0].equals("stat"))
				{
					System.out.println(ruta.stat(argv[1]));
				}
				else if (argv[0].equals("vi"))
				{
					ruta.vi(argv[1], Integer.parseInt(argv[2]));
				}
				else if (argv[0].equals("mkdir"))
				{
					ruta.mkdir(argv[1]);
				}
				else if (argv[0].equals("ln"))
				{
					ruta.ln(argv[1],argv[2]);
				}
				else if (argv[0].equals("rm"))
				{
					ruta.rm(argv[1]);
				}
				else if (argv[0].equals("exit"))
				{
					end = true;
				}
				else {
					System.out.println("Comando desconocido");
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Error sintactico: parametros insuficientes");
			}
			catch(ExcepcionArbolFicheros e)
			{
				System.out.println(e);
			}
			catch(IOException e)
			{
				System.out.println("Error de entrada-salida");
			}
		}
	}
};