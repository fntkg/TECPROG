import java.util.Iterator;

public class MainStaticStack
{
	public static void main(String[] args)
	{
		StaticStack<Integer> ss = new StaticStack<Integer>();
		ss.anyadir(42);
		ss.anyadir(15);
		ss.borrarUltimo();
		for (int i = 0; i < 100; i+=5) ss.anyadir(i);
		
		Iterator<Integer> iter = ss.iterator();
		while(iter.hasNext()) 
			System.out.print(iter.next()+" ");
		
		System.out.println();

	//Opcionalmente, con esta nomenclatura, puede hacerse asi
		for (Integer i : ss) 
			System.out.print(i+" ");
		
		System.out.println();
	}
}
