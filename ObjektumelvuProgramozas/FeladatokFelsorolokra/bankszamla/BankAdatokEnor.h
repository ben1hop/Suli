#pragma once

#include <vector>
#include <string>
#include <fstream>

typedef struct Statisztika {
	std::string szaml;
	int egyenl;
	int db;
};

typedef struct Tranzakcio {
	std::string szaml;
	std::string datum;
	int osszeg;
};


// a felsorolo statisztikakat dolgoz fel de tranzakciokat olvas be
// azt keressuk aki a minusz 100 000-es egyenelegtol kisebbel rendelkezik es a legkevesebb darab tranzakcioja van
class BankAdatokEnor
{
private:
	Statisztika curr;
	bool _end;
	std::ifstream file;
	void read();
	Tranzakcio curr_line;
	enum Status {
		Abnorm,
		Norm
	};
	Status status;

public:
	BankAdatokEnor(const std::string& fileName) : file(fileName) { /* if file fail */ };

	void first();
	void next();
	bool end() const { return _end; };
	Statisztika current() const { return curr; };

	enum Errors {
		EMPTY_FILE
	};
};

