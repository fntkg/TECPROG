#include <string>
#include <vector>
#include <iostream>
#include <sstream>

#include "ruta.h"

using namespace std;

int main()
{
	Directorio raiz("");
	Ruta ruta(raiz);

	for (bool done=false; !done; )
	{
		string         line,arg;
		vector<string> cmd;

		cout << "~> " << flush;

		getline(cin,line);
		istringstream iss(line);
		while(getline(iss,arg,' ')) cmd.push_back(arg);

		if (cin.eof())
		{
			done = true;
			continue;
		}
		if (cmd.size()<1)
			continue;

		try
		{
			if (cmd[0]=="pwd")
			{
				cout << ruta.pwd() << endl;
			}
			if (cmd[0]=="ls")
			{
				cout << ruta.ls() << endl;
			}
			if (cmd[0]=="du")
			{
				cout << ruta.du() << endl;
			}
			if (cmd[0]=="cd")
			{
				ruta.cd(cmd.at(1));
			}
			if (cmd[0]=="stat")
			{
				cout << ruta.stat(cmd.at(1));
			}
			if (cmd[0]=="vim")
			{
				ruta.vim(cmd.at(1),atoi(cmd.at(2).c_str()));
			}
			if (cmd[0]=="mkdir")
			{
				ruta.mkdir(cmd.at(1));
			}
			if (cmd[0]=="ln")
			{
				ruta.ln(cmd.at(1),cmd.at(2));
			}
			if (cmd[0]=="rm")
			{
				ruta.rm(cmd.at(1));
			}
			if (cmd[0]=="exit")
			{
				done = true;
			}
		}
		catch (out_of_range& e)
		{
			cerr << "Error sintactico: parametros insuficientes" << endl;
			cerr << e.what() << endl;
		}
		catch (arbol_ficheros_error& e)
		{
			cerr << e.what() << endl;
		}
	}
	cout << endl;

	return 0;
}
