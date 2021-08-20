#include <iostream>
#include <sstream>
#include <string>

#include "library/summation.hpp"
#include "library/linsearch.hpp"
#include "library/seqinfileenumerator.hpp"
#include "library/stringstreamenumerator.hpp"
#include "library/maxsearch.hpp"
#include "library/enumerator.hpp"

struct ForduloAdat{
	int fordulo;
	int golok;
};

struct Fordulo {
	int fordulo;
	std::string ev;
	int golok;
};

struct Merkozes {
	std::string hazai, idegen;
	int hazai_e, idegen_e;
};

class MyMaxSeach : public MaxSearch<ForduloAdat, int, Greater<int>> {
	int func(const ForduloAdat& e) const override {
		return e.golok;
	}
};

class ForduloSumm : public Summation<Fordulo, ForduloAdat> {
	int fordulo;
public:
	ForduloSumm(int f) : fordulo(f) {};

	void first() override {}

	ForduloAdat func(const Fordulo& e) const override {
		return { 0 ,e.golok };
	};
	ForduloAdat neutral() const override {
		return { fordulo, 0 };
	};
	ForduloAdat add(const ForduloAdat& a, const ForduloAdat& b) const override {
		return { a.fordulo , a.golok + b.golok };
	};
	bool whileCond(const Fordulo& e) const override {
		return e.fordulo == fordulo;
	}
};

class MySummation : public Summation<Merkozes, int> {

	int func(const Merkozes& e) const override {
		return e.hazai_e + e.idegen_e;
	};
	int neutral() const override {
		return 0;
	};
	int add(const int& a, const int& b) const override {
		return a + b;
	};
};


std::istream& operator>>(std::istream& is, Fordulo& e) {
	std::string line;
	getline(is, line);
	std::stringstream ss(line);

	ss >> e.fordulo >> e.ev;

	StringStreamEnumerator<Merkozes> enor2(ss);
	MySummation summ;
	summ.addEnumerator(&enor2);
	summ.run();

	e.golok = summ.result();
	return is;
}

std::istream& operator>>(std::istream& is, ForduloAdat& e) {
	return is;
}

class MyEnumerator : public Enumerator<ForduloAdat> {

private:
	SeqInFileEnumerator<Fordulo> _f;
	bool _end;
	ForduloAdat _current;
public:
	MyEnumerator(std::string file) : _f(file){}
	void first() override {
		_f.first();
		next();

	};
	void next() override {
		_end = _f.end();
		if (_end)
			return;
		_current.fordulo = _f.current().fordulo;

		ForduloSumm summ2(_f.current().fordulo);
		summ2.addEnumerator(&_f);
		summ2.run();
		
		_current.golok = summ2.result().golok;	

	};
	bool end() const override {
		return _end;
	};
	ForduloAdat current() const override {
		return _current;
	};
};


std::istream& operator>>(std::istream& is, Merkozes& e) {
	is >> e.hazai >> e.idegen >> e.hazai_e >> e.idegen_e;
	return is;
}



int main() {
	try {
		MyEnumerator enor("inputzh2.txt");
		MyMaxSeach max;
		max.addEnumerator(&enor);
		max.run();
		if (max.found()) {
			std::cout << "Fordulo: "<< max.optElem().fordulo << " golok:" << max.optElem().golok << std::endl;
		}
		else {
			std::cout << "Ures file.\n";
		}

	}
	catch (SeqInFileEnumerator<Fordulo>::Exceptions e) {
		std::cout << "Hianyzo file.\n";
	}
}