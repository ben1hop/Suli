#include <iostream>
#include "BankAdatokEnor.h"
#include <vector>

/*Egy szekvenciális  inputfájlban  egy  bank  ügyfeleinek  havi  kivét/betét  forgalmát  (tranzakcióit) tárolják. 
Egy tranzakció az ügyfél számlaszámából, a tranzakció dátumából, és összegéből (előjeles egész szám: negatív a kivét, pozitív a betét) áll. 
A tranzakciók számlaszám szerint rendezetten helyezkednek el. 
Gyűjtsük ki azon számlaszámokat, ahol a tranzakciók összesített összege a 100 000 Ft-os  kivételt  meghaladta,  és  ezek  közül  adjuk  meg  azt  a  számlaszámot,  
amelynél  a  havi tranzakciószám (kivétek és betétek együttes száma) a legkisebb volt.*/

using namespace std;

int main() {
	vector<string> y;
	// Min kivalasztas
	BankAdatokEnor t("input.txt");

	bool l = false;
	string sz;
	int min = 0;
	try {
		t.first();
	}
	catch (BankAdatokEnor::Errors e) {
		cout << "Empty file.\n";
		exit(-1);
	}
	while (!t.end()) {
		Statisztika curr = t.current();
		if (t.current().egyenl < -100000) {
			y.emplace_back(t.current().szaml);
		}
		if (l && t.current().egyenl < -100000) {
			if (t.current().db < min) {
				min = t.current().db;
				sz =t.current().szaml;
			}
		}
		if (!l && t.current().egyenl < -100000) {
			l = true;
			min = t.current().db;
			sz = t.current().szaml;
		}
		t.next();
	}
	cout << "Feltetelnek megfelelt: \n";
	for (int i = 0; i < y.size(); i++) {
		cout << y.at(i) << "\n";
	}
	cout << "A legkevesebb tranzakcio szamu: " << sz << "\nSzama: " << min << endl;
}