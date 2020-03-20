import java.util.Stack;
import java.util.StringTokenizer;

class Expression
{

    private class Nodo{
        String valor;
        Nodo izd, dcha;

        public Nodo(String valor) {
            this.valor = valor;
            izd = dcha = null;
        }

        public void setIzd(Nodo izd) {
            this.izd = izd;
        }

        public void setDcha(Nodo dcha) {
            this.dcha = dcha;
        }
    }

    // Raiz del arbol de terminos
	Nodo root;
    String op;

    // Cnstructores
    public Expression(String expresion) {
        parse(expresion);
    }

    private void parse(String s)
    {
        StringTokenizer st=new StringTokenizer(s);

        // Pila de términos
        // Consulta el tipo de datos 'Stack' de Java
		Stack<Nodo> stk = new Stack<Nodo>();
		Nodo t, t1, t2;

		String operacion1 = "";
		String operacion2 = "";
		boolean alreadyToken = false;
		int vez = 0;

        while (st.hasMoreTokens())
        {
            String tok = st.nextToken();

            if (esOperador(tok))
            {
                // Apilar operacion y establecer sus operandos
				t = new Nodo(tok);
				t1 = stk.pop();
				t2 = stk.pop();

				t.setDcha(t1);
				t.setIzd(t2);
				//System.out.println(t.valor + "->" + t1.valor + "->" + t2.valor);
				stk.push(t);

				// Para pintar la operación
                // Para resolver la operacion
                if (!esOperador(t1.valor) && !esOperador(t2.valor)){
                    vez++;
                    if (vez == 2){alreadyToken = true;}
                    if (alreadyToken){
                        operacion2 = operacion2.concat(t1.valor);
                        operacion2 = operacion2.concat(" ");
                        operacion2 = operacion2.concat(tok);
                        operacion2 = operacion2.concat(" ");
                        operacion2 = operacion2.concat(t2.valor);
                    }
                    else{
                        operacion1 = operacion1.concat(t1.valor);
                        operacion1 = operacion1.concat(" ");
                        operacion1 = operacion1.concat(tok);
                        operacion1 = operacion1.concat(" ");
                        operacion1 = operacion1.concat(t2.valor);
                    }
                }
                else if(esOperador(t1.valor) && !esOperador(t2.valor)){
                    if (alreadyToken){
                        operacion2 = operacion2.concat(" ");
                        operacion2 = operacion2.concat(tok);
                        operacion2 = operacion2.concat(" ");
                        operacion2 = operacion2.concat(t2.valor);
                    }
                    else {
                        operacion1 = operacion1.concat(" ");
                        operacion1 = operacion1.concat(tok);
                        operacion1 = operacion1.concat(" ");
                        operacion1 = operacion1.concat(t2.valor);
                    }
                }
                else if(!esOperador(t1.valor) && esOperador(t2.valor)){
                    // Ojo que hay que poner parentesis aqui
                    if (alreadyToken){
                        operacion2 = "( " + operacion2 + " )";
                        operacion2 = operacion2.concat(" ");
                        operacion2 = operacion2.concat(tok);
                        operacion2 = operacion2.concat(" ");
                        operacion2 = operacion2.concat(t1.valor);
                    }
                    else{
                        operacion1 = "( " + operacion1 + " )";
                        operacion1 = operacion1.concat(" ");
                        operacion1 = operacion1.concat(tok);
                        operacion1 = operacion1.concat(" ");
                        operacion1 = operacion1.concat(t1.valor);
                    }
                }
                else{
                    operacion1 = "( " + operacion1 + ")";
                    operacion1 = operacion1.concat(" ");
                    operacion1 = operacion1.concat(tok);
                    operacion1 = operacion1.concat(" ");
                    operacion2 = "( " + operacion2 + ")";
                    operacion1 = operacion1.concat(operacion2);
                }
            }
            else
            {
                // Variable o constante
                t = new Nodo(tok);
                stk.push(t);
            }
        }
        root = stk.peek(); // cima de la pila
        op = operacion1;
        stk.pop();
    }

    public String eval(SymbolTab syms)
    {
        // Devuelve el resultado de evaluar la expresion
        //Recorrer arbol en profundidad
        return String.valueOf(evaluar(root, syms));
    }

    @Override
    public String toString()
    {
        // Devuelve la cadena que representa la expresion
        return op;
    }

    private float evaluar(Nodo node, SymbolTab syms){
            if (!esOperador(node.valor)){
                // OJO con las variables
                float b;
                if (Character.isLetter(node.valor.charAt(0)))
                {
                    b = syms.get(node.valor);
                }
                else
                {
                    b = Float.valueOf(node.valor).floatValue();
                }
                return b;
            }else{
                float a = evaluar(node.izd, syms);
                float b = evaluar(node.dcha, syms);

                return calcular(a,b,node.valor);
            }
    }

    private boolean esOperador(String tok){
        return tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("/");
    }

    private float calcular(float a, float b, String operador){
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:  //Division
                return a / b;
        }
    }
};
