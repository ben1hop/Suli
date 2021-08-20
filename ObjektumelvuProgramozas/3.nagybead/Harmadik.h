#pragma once
#include "Turista.h"

class Harmadik : public Turista {
private:
	int tervezett;
	int valodi;
public:
	int ronto_tenyezo() const override {
		return valodi/50;
	};
	int valodi_letszam() override { return valodi; }
	int tervezett_letszam() const override { return tervezett; }
	int valodi_letszam(int all) override {
		switch (all) {
		case 1:
			valodi=  tervezett;
			return valodi;
		case 2:
			valodi = tervezett + tervezett * 0.1;
			return valodi;
		case 3:
			valodi = tervezett;
			return valodi;
		default:
			return 0;
		}
	};
	Harmadik(int i) {
		tervezett = i;
		valodi = 0;
	}
};