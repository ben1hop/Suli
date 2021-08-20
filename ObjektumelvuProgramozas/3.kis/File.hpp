#pragma once
#include "Entry.hpp"

class File : public Entry {
private:
	int meret;
public:
	File(int m) : meret(m) { };
	~File() override { ; }
	int getMeret() const override {
		return meret;
	}
};
