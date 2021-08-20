#pragma once

#include <fstream>
#include <string>
#include <sstream>
#include "Tipusok.h"
#include "Infile.h"


class MagasugrasEnor
{
public:
    MagasugrasEnor(const std::string& fname);

    void first();
    void next();
    Versenyek current() const { return _curr; }
    bool end() const { return _end; }
    void close() { x.close(); };
private:
    Eredmeny dx;
    Status sx;
    Versenyek _curr;
    bool _end = false;

    void read();
    Infile x;
};

