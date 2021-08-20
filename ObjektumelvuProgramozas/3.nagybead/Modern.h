#pragma once
#include "Turista.h"

class Modern : public Turista {
private:
	int tervezett;
	int valodi;
public:
	int ronto_tenyezo()const override {
		return valodi / 100;
	};

	int tervezett_letszam() const override {
		return tervezett;
	}
	int valodi_letszam() override { return valodi; }
	int valodi_letszam(int all) override {
		switch (all) {
		case 1:
			valodi = tervezett + (tervezett * 0.3);
			return valodi;
		case 2:
			valodi = tervezett + (tervezett * 0.1);
			return valodi;
		case 3:
			valodi = tervezett;
			return valodi;
		default:
			return 0;
		}
	};

	Modern(int i) {
		tervezett = i; valodi = 0;
	}
};
