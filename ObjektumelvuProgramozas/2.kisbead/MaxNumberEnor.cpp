#include "MaxNumberEnor.h"

void MaxNumberEnor::first()
{
	try {
		nextValue = read_number();
	}
	catch (MaxNumberEnor::Exception e) {
		_end = true;
		return;
	}
	next();
}

void MaxNumberEnor::next()
{
	// Firstben beolvassuk az elso szamot , ha nincs akkor end es ures file
	// Mivel egy erteket ugy kapunk hogy a kovetkezo nem egyenlo ertekik olvasunk es ezt szamoljuk meg
	// igy ugyelni kell hogy a kiolvasott szekvenciabonto erteket is felkell hasznalni a kovi nextre ezert vezettem be a lastot
	// Amit akkor allitunk at igazra amikor az utolso szekvenciabonto ertek utan probalunk kiolvasni -> ekkor a beolvasas hibat fog dobni es tudjuk hogy ez a next lesz az utolso aminek az erteket vizsgaljuk
	// Megtortenik az osszehasonlitas aztan a kovi next mar csak igaz end-el ter vissza es megszakad a ciklus
	if (last) {
		_end = true;
		return;
	}
	curr.first = nextValue;
	int step = 0;
	while (!_end && curr.first == nextValue) {
		try {
			nextValue = read_number();
		}
		catch (MaxNumberEnor::Exception e) {
			last = true;
			curr.second = step+1;
			return;
		}
		step++;
	}
	curr.second = step;

}

std::pair<int,int> MaxNumberEnor::current() const
{
	return curr;
}

bool MaxNumberEnor::end() const
{
	return _end;
}

int MaxNumberEnor::read_number() {
	std::string szam("");
	read();
	while (status == Norm && lastChar != ',') {
		szam.push_back(lastChar);
		read();
	}
	if (szam == "") {
		throw MaxNumberEnor::END_OF_FILE;
	}
	return stoi(szam);
}

void MaxNumberEnor::read()
{
	if (file >> lastChar) {
		status = Norm;
	}
	else {
		status = Abnorm;
	}
}
