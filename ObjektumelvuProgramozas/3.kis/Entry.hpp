#pragma once

class Entry {
public:
	virtual	~Entry() {};	
	virtual int getMeret() const {
		return -1;
	};
};