#include "MagasugrasEnor.h"

MagasugrasEnor::MagasugrasEnor(const std::string& fname)
{
	try{
		x = Infile(fname);
	}catch(Errors e){
		throw MISSING_FILE;
	}
}

void MagasugrasEnor::first()
{
	read();
	if (sx == Abnorm) {
		_end = true;
		throw EMPTY_FILE;
		return;
	}
	next();
}

void MagasugrasEnor::next()
{
	// Megszamlalas
	if (Abnorm == sx) {
		_end = true;
		x.close();
	}
	_curr.ev = dx.datum;
	_curr.versenyzok = 0;
	while (sx == Norm && dx.datum == _curr.ev) {
		if (dx.megfelelt) {
			_curr.versenyzok++;
		}
		x.read(sx,dx);
	}
}

void MagasugrasEnor::read()
{
	x.read(sx,dx);
}
