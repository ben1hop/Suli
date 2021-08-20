#include <iostream>
#include <sstream>
#include <string>

#include "library/summation.hpp"
#include "library/linsearch.hpp"
#include "library/seqinfileenumerator.hpp"
#include "library/stringstreamenumerator.hpp"

struct ForduloAdat {
	int fordulo;
	int dontetlen;
};

struct Merkozes {
	std::string hazai, idegen;
	int hazai_e, idegen_e;
};

class MySummation : public Summation<Merkozes, int> {
	int func(const Merkozes& e) const override {
		return 1;
	};
	int neutral() const override {
		return 0;
	};
	int add(const int& a, const int& b) const override {
		return a + b;
	};
	bool cond(const Merkozes& e) const override { 
		return e.hazai_e == 0 && e.idegen_e == 0;
	};
};

class MyLinSearch : public LinSearch<ForduloAdat, false> {
	bool cond(const ForduloAdat& e) const override {
		return e.dontetlen > 3;
	}
};



std::istream& operator>>(std::istream& is, ForduloAdat& e) {
	std::string line;
	getline(is, line);
	std::stringstream ss(line);

	ss >> e.fordulo;

	StringStreamEnumerator<Merkozes> enor2(ss);
	MySummation summ;
	summ.addEnumerator(&enor2);
	summ.run();

	e.dontetlen = summ.result();
	return is;
}

std::istream& operator>>(std::istream& is, Merkozes& e) {
	is >> e.hazai >> e.idegen >> e.hazai_e >> e.idegen_e;
	return is;
}


int main() {
	try {
		SeqInFileEnumerator<ForduloAdat> enor("inputzh.txt");
		MyLinSearch search;
		search.addEnumerator(&enor);
		search.run();
		if (search.found()) {
			std::cout << "Az elso ilyen fordulo: " << search.elem().fordulo << std::endl;
		}
		else {
			std::cout << "Nincs ilyen fordulo.\n";
		}
	}
	catch (SeqInFileEnumerator<ForduloAdat>::Exceptions e) {
		std::cout << "File nemletezik!\n";
	}

}