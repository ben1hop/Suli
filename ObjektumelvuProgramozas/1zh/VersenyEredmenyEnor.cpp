#include "VersenyEredmenyEnor.h"
#include <sstream>

using namespace std;

VersenyEredmenyEnor::VersenyEredmenyEnor(const string& fileName) : file(fileName) {
	if (!file.is_open()) {
		throw VersenyEredmenyEnor::Errors::FAIL_TO_OPEN;
	}
}

void VersenyEredmenyEnor::first()
{
	read();
	if (status == Abnorm) {
		throw VersenyEredmenyEnor::Errors::EMPTY_FILE;
	}
	next();
}

void VersenyEredmenyEnor::next()
{
	// optimista lin kereses + osszegzes
	_end = status == Abnorm;
	curr.ermek = 0;
	curr.megfelelt = true;
	curr.nev = curr_line.nev;
	while (status == Norm && !curr.nev.compare(curr_line.nev)) {
		if (!curr_line.megfelelt && curr.megfelelt) {
			curr.megfelelt = false;
		}
		curr.ermek += curr_line.ermek;
		read();
	}
}

void VersenyEredmenyEnor::read()
{
	// pesszimista lin kereses + szamlalas
	string line,temp;
	int ered;
	curr_line.ermek = 0;
	curr_line.megfelelt = false; 
	if (file >> curr_line.nev >> temp) {
		temp.clear();
		status = Norm;
		getline(file , line);
		istringstream is(line);
		while (!is.eof()) {
			is >> temp;
			// pesszimista lin kereses
			if (!curr_line.megfelelt && !temp.compare("futas")) {
				curr_line.megfelelt = true;
			}
			is >> ered;
			if (ered < 4) {
				curr_line.ermek++;
			}
			temp.clear();
		}
	}
	else {
		status = Abnorm;
	}
}