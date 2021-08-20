#pragma once

#include "Turista.h"
#include "Japan.h"
#include "Modern.h"
#include "Harmadik.h"
#include <string>
#include <sstream>
#include <iostream>

#define BEVETEL_FO 1
#define BEVETEL_ALSO 10000


class Varos {
private:
	int bevetel;
	int allapot_index;
	static Varos* _instance;
protected:
	Varos(const int& allapot) {
		allapot_index = allapot;
		bevetel = 0;
	}

public:
	static void destroy_varos() { if (_instance != nullptr) delete _instance; _instance = nullptr; }
	static Varos* create_varos(const int& allapot) {
		if (_instance == nullptr) {
			_instance = new Varos(allapot);
		}
		return _instance;
	}

	int get_allapot() { return allapot_index; }

	int curr_allapot() {
		if (_instance->allapot_index > 67) {
			return 1;
		}
		else if (_instance->allapot_index <= 67 && _instance->allapot_index >= 34) {
			return 2;
		}
		else {
			return 3;
		}
	}

	void reset_bevetel() {
		_instance->bevetel = 0;
	}

	void allapot_valltas(int ertek) {
		_instance->allapot_index += ertek;
		_instance->allapot_index = (_instance->allapot_index > 100) ? 100 : _instance->allapot_index;
		_instance->allapot_index = (_instance->allapot_index < 1) ? 1 : _instance->allapot_index;

	}

	int curr_bevetel() const {
		return bevetel;
	}

	std::string status() const {
		std::stringstream str;
		str << "Bevetel: ";
		str << _instance->bevetel << "*10^5";
		str << "\nAllapotIndex: ";
		str << _instance->allapot_index;
		str << std::endl;
		return str.str();
	}


	int eves_latogatas(Turista* japanok , Turista* modernek ,Turista* harmadikok) {
		_instance->bevetel =  
			(japanok->valodi_letszam(curr_allapot()) 
				+ modernek->valodi_letszam(curr_allapot())
				+ harmadikok->valodi_letszam(curr_allapot()));
		return -1*(japanok->ronto_tenyezo() + modernek->ronto_tenyezo() + harmadikok->ronto_tenyezo());
	}

	int eves_fejlesztes() {
		if (bevetel > BEVETEL_ALSO) {
			return (_instance->bevetel - BEVETEL_ALSO) / 200;
			}
		return 0;
	}

};