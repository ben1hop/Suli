#pragma once
#include <initializer_list>
#include <vector>
#include "Entry.hpp"

class FileSystem {
	std::vector<Entry*> entries;
public:
	FileSystem(std::initializer_list<Entry*> entr) {
		for (auto i : entr) entries.emplace_back(i);
	}

	void clear() const {
		for (int i = 0; i < entries.size(); i++) {
			entries.at(i)->~Entry();
			delete entries.at(i);
		}
	}

	std::vector<Entry*> getEntries()const{
		return entries;
	}
};
