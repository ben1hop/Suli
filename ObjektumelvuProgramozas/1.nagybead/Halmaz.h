#ifndef HALMAZ_H
#define HALMAZ_H

#include <vector>
#include <iostream>

class Halmaz
{
private:
	std::vector<int> seq;
	int maxElem;
public:
	Halmaz() {};

	enum Exceptions{NOT_EXISTING,EMPTY_LIST};

	void put( int e );
	void remove( int e ); // ez dob not existinget
	bool empty( ) const;
	int maxElement( ) const; // ez dob ures listat
	int benne_van(const int& e) const;
	int max_kiv() const;
	int length() const { return seq.size(); }
	
	friend std::ostream& operator<< ( std::ostream& s, const Halmaz& a );

};
#endif