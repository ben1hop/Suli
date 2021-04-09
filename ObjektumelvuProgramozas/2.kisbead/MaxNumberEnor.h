#ifndef MAXNUMBERENOR_H
#define MACNUMBERENOR_H

#include <string>
#include <fstream>

class MaxNumberEnor {
public:
    MaxNumberEnor(const std::string& filename) : file(filename) {};
    void first();
    void next();
    std::pair<int,int> current() const;
    bool end() const;

private:
    void read();
    int read_number();
    enum Status {
        Norm,
        Abnorm
    };

    enum Exception {
        END_OF_FILE
    };
    
    std::ifstream file;
    Status status;
    std::pair<int,int> curr;
    
    char lastChar;
    int nextValue;
    bool _end = false;
    bool last = false;
};

#endif