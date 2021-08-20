#pragma once

#include "Varos.hpp"

class Turista {
protected:
	int tervezett;
	Turista() {};
public:
	virtual int tervezett_letszam() const { return 0; }

	virtual int ronto_tenyezo() const {
		return 0;
	};
	virtual int valodi_letszam() { return 0; }
	virtual int valodi_letszam(int all) { return 0; }
};