#pragma once

#include <string>

struct Versenyek {
    int ev;
    int versenyzok = 0;
};

struct Eredmeny {
	std::string nev;
	int datum;
	int ermek = 0;
	bool megfelelt = false;
};

enum Status { Norm , Abnorm};
enum Errors { EMPTY_FILE , MISSING_FILE};
