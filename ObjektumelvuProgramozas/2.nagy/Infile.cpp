#include "Infile.h"

using namespace std;

Infile::Infile(const std::string& fname)
{
    f.open(fname);
    if (f.fail()) throw MISSING_FILE;
}

void clear_dx(Eredmeny& dx) {
    dx.nev.clear();
    dx.datum = 0;
    dx.megfelelt = false;
    dx.ermek = 0;
}

bool Infile::read(Status& sx, Eredmeny& dx)
{
    string line;
    getline(f, line, '\r');
    sx = f.fail() ? Abnorm : Norm;
    if (Norm == sx) {
        istringstream in(line);
        clear_dx(dx);
        string block;
        in >> block;
        while (block.at(0) > 57 || block.at(0) < 48) { // isdigit lehal ekezeten
            dx.nev = dx.nev + block + " ";
            block.clear();
            in >> block;
        };
        dx.nev.pop_back(); // az utolso hozza adott space-t toroljuk
        dx.datum = atoi(block.c_str()); // a legutobb kiolvasott string pedig a datum
        pair<string,int> eredmenyek;
        while (in >> eredmenyek.first >> eredmenyek.second) {
            if (!dx.megfelelt && "magasugras" == eredmenyek.first) {
                dx.megfelelt = true;
            }
            if (eredmenyek.second == 1) {
                dx.ermek++;
            }
        }
    }

    return Norm == sx;
}
