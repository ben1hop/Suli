#include "BankAdatokEnor.h"
#include <fstream>
#include <sstream>

using namespace std;



void BankAdatokEnor::first()
{
	
	read();
	if (status == Abnorm) {
		throw BankAdatokEnor::Errors::EMPTY_FILE;
	}
	next();
}

void BankAdatokEnor::next()
{
	_end = status == Abnorm; // vege:= sx.abnorm
	if (!_end) {
		curr.szaml = curr_line.szaml;
		curr.db = 0;
		curr.egyenl = 0;
		while (status == Norm && curr_line.szaml == curr.szaml) {
			curr.egyenl = curr.egyenl + curr_line.osszeg;
			curr.db++;
			read();
		}
	}
}

void BankAdatokEnor::read()
{
	if (file >> curr_line.szaml >> curr_line.datum >> curr_line.osszeg ) {
		status = Norm;
	}
	else {
		status = Abnorm;
	}
}
