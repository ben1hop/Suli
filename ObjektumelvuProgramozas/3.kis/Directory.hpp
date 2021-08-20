#pragma once
#include <initializer_list>
#include <vector>
#include "Entry.hpp"


class Directory : public Entry {
private:
	std::vector<Entry*> bejegyzesek;
public:
	Directory(std::initializer_list<Entry*> entr) {
		for (auto i : entr) bejegyzesek.emplace_back(i);
	}
	~Directory() override {
		for (auto i : bejegyzesek) delete i;
	}
	int getMeret() const override{
		int s = 0;
		for (auto i : bejegyzesek) s = s + i->getMeret();
		return s;
	}
};