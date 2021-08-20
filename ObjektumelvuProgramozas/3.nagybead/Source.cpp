#include "Varos.hpp"
#include "Japan.h"
#include "Modern.h"
#include "Harmadik.h"
#include "Turista.h"

#include <string>
#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

Varos* Varos::_instance = nullptr;

enum ERRORS{ KEVES_EV };

void destroy(vector<vector<Turista*>>& vec)
{
	for (unsigned int i = 0; i < vec.size(); ++i){
		delete vec.at(i)[0];
		delete vec.at(i)[1];
		delete vec.at(i)[2];
	}
}

void print_elott(const Varos* varos, const int& japanok, const int& modernek, const int& harmadikok) {
	std::cout << "-----Szezon elott--------\n";
	std::cout << "Tervezett latogatok:\nJapanok: " << japanok << " Modernek: " << modernek << " Harmadikok: " << harmadikok << std::endl;
	std::cout << varos->status();
}


void print_utan(const Varos* varos , const int& japanok, const int& modernek, const int& harmadikok) {
	std::cout << "--------Szezon utan:--------\n";
	std::cout << "Tenyleges latogatok:\nJapanok: " << japanok << " Modernek: " << modernek << " Harmadikok: " << harmadikok << std::endl;
	std::cout << varos->status();
	std::cout << std::endl;
}



void szimulacio(Varos* varos, vector < vector<Turista*>>& turistak, int ev) {
	if (ev < 10) { throw KEVES_EV; }
	varos->reset_bevetel();
	int j = 0;
	for (int i = 0; i < 10 && !(i == ev); i++) {
		cout << "        " << i + 1 << ".ev\n";
		print_elott(varos, turistak.at(i)[0]->tervezett_letszam(),
			turistak.at(i)[1]->tervezett_letszam(),
			turistak.at(i)[2]->tervezett_letszam());

		int a = varos->eves_latogatas(turistak.at(i)[0], turistak.at(i)[1], turistak.at(i)[2]);
		int b = varos->eves_fejlesztes();

		varos->allapot_valltas(a + b);

		print_utan(varos, turistak.at(i)[0]->valodi_letszam(),
			turistak.at(i)[1]->valodi_letszam(),
			turistak.at(i)[2]->valodi_letszam());
		
	}
}

std::string allapot(int allapot) {
	switch (allapot) {
	case 1:
		return "Jo allapotu.\n";
	case 2:
		return "Atlagos allapotu.\n";
	case 3:
		return "Lepusztult allapotu.\n";
	default:
		return "";
	}

}

void populate(int* ev , int* kezdo , const string& input, vector<vector<Turista*>>& turistak) {
	ifstream read(input);
	read >> *kezdo;
	read >> *ev;
	turistak.resize(*ev);
	int j, m, h;
	for (int i = 0; i < *ev; i++) {
		read >> j >> m >> h;
		turistak.at(i).emplace_back(new Japan(j));
		turistak.at(i).emplace_back(new Modern(m));
		turistak.at(i).emplace_back(new Harmadik(h));
	}
	read.close();
}
	
#ifdef NORMAL_MODE

int main() {
	int kezdo ,ev;
	vector < vector<Turista*> > turistak;
	populate(&ev ,&kezdo , "test1.txt" ,turistak);
	Varos* varos = Varos::create_varos(kezdo);
	
	try {
		szimulacio(varos, turistak, ev);
	}
	catch (ERRORS e) {
		cout << "Kevesebb mint 10 ev van megadva.\n";
	}


	cout << allapot(varos->curr_allapot());
	destroy(turistak);
	Varos::destroy_varos();
	varos = nullptr;
	
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

TEST_CASE("nincsenek evek", "test1.txt")
{
	int ev, kezdo;
	vector < vector < Turista*>> turistak;
	populate(&ev,&kezdo,"test1.txt",turistak);
	Varos* varos = Varos::create_varos(kezdo);
	int curr_allapot = varos->curr_allapot();
	
	REQUIRE_THROWS_AS(szimulacio(varos, turistak, ev), ERRORS);
	CHECK(varos->curr_allapot() == curr_allapot);
	CHECK(turistak.size() == ev);

	destroy(turistak);
	Varos::destroy_varos();
	varos = nullptr;
}

TEST_CASE("kevesebb mint 10 ev van megadva", "test2.txt")
{
	int ev, kezdo;
	vector < vector < Turista*>> turistak;
	populate(&ev, &kezdo, "test2.txt", turistak);
	Varos* varos = Varos::create_varos(kezdo);
	int curr_allapot = varos->curr_allapot();

	REQUIRE_THROWS_AS(szimulacio(varos, turistak, ev), ERRORS);
	CHECK(varos->curr_allapot() == curr_allapot);
	CHECK(turistak.size() == ev);

	destroy(turistak);
	Varos::destroy_varos();
	varos = nullptr;
}

TEST_CASE("tobb mint 10 ev van megadva", "test3.txt")
{
	int ev, kezdo;
	vector < vector < Turista*>> turistak;
	populate(&ev, &kezdo, "test3.txt", turistak);
	Varos* varos = Varos::create_varos(kezdo);
	int curr_allapot = varos->curr_allapot();
	szimulacio(varos, turistak, ev);

	CHECK(varos->curr_allapot() != curr_allapot);
	CHECK(turistak.size() == ev);

	destroy(turistak);
	Varos::destroy_varos();
	varos = nullptr;
}

TEST_CASE("bevetel tesztek")
{
	vector< vector< Turista*>> turistak;
	turistak.resize(1);
	SECTION("rossz állapot") {
		Varos* varos = Varos::create_varos(1);	
		turistak.at(0).emplace_back(new Japan(1000));
		turistak.at(0).emplace_back(new Modern(2000));
		turistak.at(0).emplace_back(new Harmadik(3000));
		varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]);

		CHECK(varos->curr_bevetel() == (0 + 2000 + 3000));
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}

	SECTION("közepes állapot") {
		Varos* varos = Varos::create_varos(50);
		turistak.at(0).emplace_back(new Japan(1000));
		turistak.at(0).emplace_back(new Modern(2000));
		turistak.at(0).emplace_back(new Harmadik(3000));
		varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]);

		CHECK(varos->curr_bevetel() == (1000 + 2200 + 3300));
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}

	SECTION("jó állapot") {
		Varos* varos = Varos::create_varos(100);
		turistak.at(0).emplace_back(new Japan(1000));
		turistak.at(0).emplace_back(new Modern(2000));
		turistak.at(0).emplace_back(new Harmadik(3000));
		varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]);

		CHECK(varos->curr_bevetel() == (1200 + 2600 + 3000));
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}

}

TEST_CASE("allapot valtozas tesztek")
{
	vector< vector< Turista*>> turistak;
	turistak.resize(1);
	SECTION("rossz allapot") {
		Varos* varos = Varos::create_varos(1);
		turistak.at(0).emplace_back(new Japan(2000));
		turistak.at(0).emplace_back(new Modern(9000));
		turistak.at(0).emplace_back(new Harmadik(3000));
		

		CHECK(varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]) == -1*(0 * 0 + (9000 / 100) + (3000 / 50)));
		CHECK(varos->eves_fejlesztes() == (varos->curr_bevetel() - 10000) / 200);
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}

	SECTION("kozepes allapot") {
		Varos* varos = Varos::create_varos(50);
		turistak.at(0).emplace_back(new Japan(2000));
		turistak.at(0).emplace_back(new Modern(9000));
		turistak.at(0).emplace_back(new Harmadik(3000));


		CHECK(varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]) == -1 * (2000 * 0 + (9900 / 100) + (3300 / 50)));
		CHECK(varos->eves_fejlesztes() == (varos->curr_bevetel() - 10000) / 200);
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}

	SECTION("jo allapot") {
		Varos* varos = Varos::create_varos(100);
		turistak.at(0).emplace_back(new Japan(2000));
		turistak.at(0).emplace_back(new Modern(9000));
		turistak.at(0).emplace_back(new Harmadik(3000));


		CHECK(varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]) == -1 * (2400 * 0 + (11700 / 100) + (3000 / 50)));
		CHECK(varos->eves_fejlesztes() == (varos->curr_bevetel() - 10000) / 200);
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}

	SECTION("nincs eleg bevetel") {
		Varos* varos = Varos::create_varos(1);
		turistak.at(0).emplace_back(new Japan(1000));
		turistak.at(0).emplace_back(new Modern(5000));
		turistak.at(0).emplace_back(new Harmadik(2000));

		varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]);
		CHECK(varos->eves_fejlesztes() == 0);
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}
	SECTION("javitas tullepi a felso hatart") {
		Varos* varos = Varos::create_varos(100);
		turistak.at(0).emplace_back(new Japan(10000));
		turistak.at(0).emplace_back(new Modern(0));
		turistak.at(0).emplace_back(new Harmadik(0));
		int a = varos->eves_fejlesztes();

		varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]);
		CHECK(varos->eves_fejlesztes() == (12000 - 10000) / 200);
		CHECK(varos->get_allapot() == 100);
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}
	SECTION("rontas tullepi az also hatart") {
		Varos* varos = Varos::create_varos(1);
		turistak.at(0).emplace_back(new Japan(0));
		turistak.at(0).emplace_back(new Modern(0));
		turistak.at(0).emplace_back(new Harmadik(11000));
		int a = varos->eves_fejlesztes();

		varos->eves_latogatas(turistak.at(0)[0], turistak.at(0)[1], turistak.at(0)[2]);
		CHECK(varos->eves_fejlesztes() == (11000 - 10000) / 200);
		CHECK(varos->get_allapot() == 1);
		destroy(turistak);
		Varos::destroy_varos();
		varos = nullptr;
	}
}



#endif // NORMAL_MODE