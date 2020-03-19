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
                // Si comienza por un caracter no numerico,
                // es un nombre de variable
                /*if (Character.isLetter(tok.charAt(0)))
                {
                    // Apilar variable
					t = new Nodo(tok);
					stk.push(t);
                }
                else
                {
                    float v = Float.valueOf(tok).floatValue();
                    // Apilar constante

                }*/
            }
        }
        root = stk.peek(); // cima de la pila
        op = operacion1;
        stk.pop();
    }

    public String eval(SymbolTab syms)
    {
        // Devuelve el resultado de evaluar la expresion
        return recorrer(root, "");
    }

    @Override
    public String toString()
    {
        // Devuelve la cadena que representa la expresion
        return op;
    }

    private String recorrer(Nodo node, String op){
        if (node != null){
            recorrer(node.izd, op);
            op = op.concat(node.valor);
            op = op.concat(" ");
            //System.out.println("Op: " + op);
            //System.out.print(op);
            recorrer(node.dcha, op);
        }
        //System.out.print(op);
        return op;
    }

    private float evaluar(Nodo node, float result){
        if(node != null){
            evaluar(node.izd, result);
            //Calcular valor numerico del string en cuestion



            evaluar(node.dcha, result);
            return result;
        }
        else{
            return result;
        }
    }

    private boolean esOperador(String tok){
        if (tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("/")){
            return true;
        }
        else{
            return false;
        }
    }
};

