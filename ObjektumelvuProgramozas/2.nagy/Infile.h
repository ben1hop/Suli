#pragma once
#include <string>
#include <fstream>
#include <sstream>
#include "Tipusok.h"

using namespace std;

class Infile
{
public:
	Infile(const string& fileName);
	Infile() {};
	bool read(Status& sx, Eredmeny& dx);
	void close() { f.close(); }
private:
	ifstream f;
};

