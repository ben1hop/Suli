#ifndef COMPLEX_H
#define COMPLEX_H

#include <ostream>

class Complex {
public:
    float x, y;
    Complex(float x_, float y_);
    Complex();

    enum ERRORS { NULL_DIVISION };
    friend Complex operator+(const Complex& a, const Complex& b);
    friend Complex operator-(const Complex& a, const Complex& b);
    friend Complex operator*(const Complex& a, const Complex& b);
    friend Complex operator/(const Complex& a, const Complex& b);
    friend std::ostream& operator<<(std::ostream& out, const Complex& a);
};

#endif