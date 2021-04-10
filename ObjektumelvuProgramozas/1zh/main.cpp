#include <iostream>
#include "VersenyEredmenyEnor.h"
#include <string>

using namespace std;

//void print(const vector<TanuloEredmeny>& c) {
//	for (int i = 0; i < c.size(); i++) {
//		cout << c.at(i).nev << " " << c.at(i).ermek<< endl;
//	}	
//}

int main() {
	//Kivalogatas -> feltetel hogy voltak futason minden evben
	//vector<TanuloEredmeny> c;
	VersenyEredmenyEnor t;
	try {
		t = VersenyEredmenyEnor("input.txt");
		t.first();
	}
	catch (VersenyEredmenyEnor::Errors e) {
		if (e == VersenyEredmenyEnor::Errors::EMPTY_FILE) {
			cout << "Ures file.\n";
		}
		if (e == VersenyEredmenyEnor::Errors::FAIL_TO_OPEN) {
			cout << "Nemsikerult megnyitni a file-t.\n";
		}
		exit(-1);
	}
	cout << "Megfeleltek:\n";
	while (!t.end()) {
		TanuloEredmeny curr = t.current();
		if (t.current().megfelelt) {
			//c.emplace_back(t.current());
			cout << t.current().nev << " " << t.current().ermek << "db"<< endl;
		}
		t.next();
	}
}