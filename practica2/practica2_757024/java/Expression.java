import java.util.Stack;
import java.util.StringTokenizer;

class Expression
{
    // Raiz del arbol de terminos
	Stack<String> root;

    // Cnstructores
    public Expression(String expresion) {
        parse(expresion);
    }

    private void parse(String s)
    {
        StringTokenizer st=new StringTokenizer(s);

        // Pila de términos
        // Consulta el tipo de datos 'Stack' de Java
		Stack<String> stk;

        while (st.hasMoreTokens())
        {
            String tok = st.nextToken();

            if (tok.equals("+"))
            {
                // Apilar suma
				...
            }
            else if (tok.equals("-"))
            {
                // Apilar resta
				...
            }
            else if (tok.equals("*"))
            {
                // Apilar producto
				...
            }
            else if (tok.equals("/"))
            {
                // Apilar cociente
				...
            }
            else
            {
                // Variable o constante
                // Si comienza por un caracter no numerico,
                // es un nombre de variable
                if (Character.isLetter(tok.charAt(0)))
                {
                    // Apilar variable
					...
                }
                else
                {
                    float v = Float.valueOf(tok).floatValue();
                    // Apilar constante
					...
                }
            }
        }
        root = // cima de la pila
    }

    public float eval(SymbolTab syms)
    {
        // Devuelve el resultado de evaluar la expresion
    }

    @Override
    public String toString()
    {
        // Devuelve la cadena que representa la expresion
    }
};

