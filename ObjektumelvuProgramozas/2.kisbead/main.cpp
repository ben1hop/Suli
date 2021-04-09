#include "MaxNumberEnor.h"
#include <iostream>

int main() {
	MaxNumberEnor enor("input.txt");
	std::pair<int, int> max;
	enor.first();
	if (!enor.end()) {		
		max = enor.current();
		enor.next();
		while (!enor.end()) {
			if (enor.current().second > max.second) {				
				max.first = enor.current().first;
				max.second = enor.current().second;
			}
			enor.next();
		}
		std::cout << "Max:" << max.first << " " << "Db:" << max.second << std::endl;
	}
	else {
		std::cout << "Ures File.\n";
	}	
}