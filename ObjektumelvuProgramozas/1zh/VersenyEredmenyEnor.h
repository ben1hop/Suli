#pragma once

#include <string>
#include <vector>
#include <fstream>

using namespace std;

struct TanuloEredmeny {
	string nev;
	int ermek;
	bool megfelelt;
};

class VersenyEredmenyEnor
{
private:
	ifstream file;
	bool _end;
	TanuloEredmeny curr;
	enum Status {
		Norm, Abnorm
	};
	Status status;
	void read();
	TanuloEredmeny curr_line;

public:
	VersenyEredmenyEnor(const string& fileName);
	VersenyEredmenyEnor() {};
	void first();
	void next();
	TanuloEredmeny current() const { return curr; };
	bool end() const { return _end; }
	enum Errors {
		EMPTY_FILE, FAIL_TO_OPEN
	};
};