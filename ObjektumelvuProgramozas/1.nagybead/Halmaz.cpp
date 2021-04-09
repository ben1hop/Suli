#include "Halmaz.h"

void Halmaz::put(int e)
{
	if (seq.empty() || maxElem < e)
		maxElem = e;
	seq.push_back(e);
}

void Halmaz::remove(int e)
{
	if (seq.empty())
		throw Halmaz::EMPTY_LIST;
	seq.erase(seq.begin()+benne_van(e));
	if (e == maxElem) {
		if (seq.empty()) {
			maxElem = 0;
		}
		else {
			maxElem = max_kiv();
		}

	}
}

bool Halmaz::empty() const
{
	return seq.size() == 0;
}

int Halmaz::maxElement() const
{
	if (seq.empty())
		throw Halmaz::EMPTY_LIST;
	else
		return maxElem;

}

int Halmaz::benne_van(const int& e) const
{
	int i = 0;
	while (i < seq.size()) {
		if (seq.at(i) == e) {
			return i;
		}
		i++;
	}
	throw Halmaz::NOT_EXISTING;
}

int Halmaz::max_kiv() const
{
	int ind = 0, max = seq.at(ind);
	for (int i = 0; i < seq.size(); i++) {
		if (seq.at(i) > max) {
			max = seq.at(i);
			ind = i;
		}
	}
	return max;
}

std::ostream& operator<<(std::ostream& s, const Halmaz& a)
{
	if (a.seq.empty())
		s << "Ures lista.";
	else
		for (int i = 0; i < a.seq.size(); i++) {
			if (i == a.seq.size() - 1)
				s << a.seq.at(i);	
			else
				s << a.seq.at(i) << ",";
		}
	return s;
}
