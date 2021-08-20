#include <iostream>
#include <vector>
#include "Infile.h"
#include "MagasugrasEnor.h"
#include "Tipusok.h"

using namespace std;

bool elso(const string& fname , Eredmeny& q) {
	Infile x(fname);
	Status sx = Norm;
	Eredmeny dx;
	
	if (!x.read(sx, dx)) { throw EMPTY_FILE; }
	else {
		while (sx == Norm) {
			if (dx.megfelelt && dx.ermek > 2) {
				q = dx;
				x.close();
				return true;
			}
			x.read(sx,dx);
		}
	}
	x.close();
	return false;
}

bool masodik(const string& fname, vector<int>& q) {
	MagasugrasEnor t(fname);
	// Felteteles osszegzes
	t.first();
	if (t.end()) { throw EMPTY_FILE; }; // ez valamiert gcc-n mindig lehal
	for (; !t.end(); t.next()) {
		if (t.current().versenyzok > 9) {
			q.push_back(t.current().ev);
		}
	}
	t.close();
	return q.size()>0;
}

void print(const std::vector<int>& q) {
	for (int ev : q) {
		cout << ev << " ";
	}
	cout << endl;
}

#ifdef NORMAL_MODE

int main() {
	try {
		Eredmeny q;
		if (elso("case3.txt", q)) {
			cout << q.nev << "volt magasugrason "<< q.datum << "-ban es arany ermei:" << q.ermek << "db (>=3)\n";
		}
		else {
			cout << "Nemvolt ilyen ember.\n";
		}
	}
	catch (Errors e) {
		if (e == EMPTY_FILE) {
			cout << "Ures file.\n";
		}
		if (e == MISSING_FILE) {
			cout << "Hianyzo file.\n";
		}
	}
	try {
		std::vector<int> q;
		if (masodik("ures.txt", q)) {
			cout << "Az evek listaja: \n";
			print(q);
		}
		else {
			cout << "Nemvolt ilyen ev.\n";
		}
	}
	catch (Errors e) {
		if (e == EMPTY_FILE) {
			cout << "Ures file.\n";
		}
		if (e == MISSING_FILE) {
			cout << "Hianyzo file\n.";
		}
	}
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

using namespace std;

TEST_CASE("empty file") {
	Eredmeny a;
	vector<int> b;
	REQUIRE_THROWS_AS(elso("ures.txt", a) , Errors);
	REQUIRE_THROWS_AS(masodik("ures.txt",b) , Errors);
}

TEST_CASE("missing file") {
	Eredmeny a;
	vector<int> b;
	REQUIRE_THROWS_AS(elso("nemletezo.txt", a), Errors);
	REQUIRE_THROWS_AS(masodik("nemletezo.txt", b), Errors);
}

TEST_CASE("Task(1)_Case:1 nincsen magasugro") {
	Eredmeny a;
	CHECK_FALSE(elso("case1.txt",a));
}

TEST_CASE("Task(1)_Case:2 van magasugro de nincs 3 elso helye") {
	Eredmeny a;
	CHECK_FALSE(elso("case1.txt", a));
}

TEST_CASE("Task(1)_Case:3 letezik megfelelo egyen") {
	Eredmeny a;
	CHECK(elso("case3.txt", a));
	CHECK(a.datum == 2020);
	CHECK(a.ermek == 4);
	CHECK(a.nev == "Nagyon ugyes Inna");
}
TEST_CASE("Task(1)_Case:4 letezik megfelelo egyen - utolso elem") {
	Eredmeny a;
	CHECK(elso("case4.txt", a));
	CHECK(a.datum == 2020);
	CHECK(a.ermek == 4);
	CHECK(a.nev == "Nagyon ugyes Inna");
}

TEST_CASE("Task(2)_Case:1 nemletezik magasugrasos ev") {
	vector<int> a;
	CHECK_FALSE(masodik("case1.txt" , a));
}

TEST_CASE("Task(2)_Case:2 letezik magasugros ev de osszesitve nincs 10 indulo") {
	vector<int> a;
	CHECK_FALSE(masodik("case2.txt", a));
}

TEST_CASE("Task(2)_Case:3 letezik magasugros ev es van 10 induloja") {
	vector<int> a;
	CHECK(masodik("case3.txt", a));
	CHECK(a.at(0) == 2020);
	CHECK(a.size() == 1);
}

TEST_CASE("Task(2)_Case:4 letezik magasugros ev es van 10 induloja - utolso elem") {
	vector<int> a;
	CHECK(masodik("case5.txt", a));
	CHECK(a.at(0)==2020);
	CHECK(a.size() == 1);
}

#endif