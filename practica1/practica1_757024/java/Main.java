public class Main
{
	//TODO: Define la cabecera de este metodo aprovechando la herencia para que sea equivalente
	//a declarar los dos metodos siguientes:
	//public static void anyadir_elementos(StaticStack<Integer> ss)
	//public static void anyadir_elementos(DynamicStack<Integer> ss)
	public static void anyadir_elementos(Stack<Integer> ss)
	{
		ss.push(42);
		ss.push(15);
		ss.pop();
		for (int i = 0; i < 100; i+=5) ss.push(i);
	}

	//TODO: Define la cabecera de este metodo aprovechando la herencia para que sea equivalente
	//a declarar los dos metodos siguientes:
	//public static void mostrar_elementos(StaticStack<Integer> ss)
	//public static void mostrar_elementos(DynamicStack<Integer> ss)
	public static void mostrar_elementos(Stack<Integer> ss)
	{
		for (Integer i : ss)
			System.out.print(i+" ");
		System.out.println();
	}

	public static void main(String[] args)
	{
		StaticStack<Integer> ss_e = new StaticStack<Integer>();
		DynamicStack<Integer> ss_d = new DynamicStack<Integer>();

		anyadir_elementos(ss_e);
		anyadir_elementos(ss_d);

		mostrar_elementos(ss_e);
		mostrar_elementos(ss_d);
	}
}
