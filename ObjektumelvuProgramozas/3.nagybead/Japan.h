#pragma once
#include "Turista.h"

class Japan : public Turista {
private:
	int tervezett;
	int valodi;
public:
	int tervezett_letszam() const override {
		return tervezett;
	}

	int ronto_tenyezo() const override {
		return 0;
	};
	int valodi_letszam() override { return valodi; }
	int valodi_letszam(int all) override {
		switch (all) {
		case 1:
			 valodi = tervezett + (tervezett * 0.2);
			 return valodi;
		case 2:
			valodi = tervezett;
			return valodi;
		case 3:
			return 0;
		default:
			return 0;
		}

	};

	Japan(int i) {
		tervezett = i;
		valodi = 0;
	}
};