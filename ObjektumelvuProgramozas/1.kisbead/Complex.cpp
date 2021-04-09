#include "./Complex.h"


Complex::Complex(float x_, float y_) : x(x_), y(y_) {}

Complex::Complex() { x = 0.f; y = 0.f; }

Complex operator+(const Complex& a, const Complex& b)
{
	return Complex (a.x + b.x, a.y + b.y);
}

Complex operator-(const Complex& a, const Complex& b)
{
	return Complex (a.x - b.x, a.y - b.y);
}

Complex operator*(const Complex& a, const Complex& b)
{
	return Complex (
		((a.x * b.x) - (a.y * b.y)), 
		((a.x * b.y) + (a.y * b.x))
	);
}

Complex operator/(const Complex& a, const Complex& b)
{
	if (b.x == 0 && b.y == 0) {
		throw Complex::NULL_DIVISION;
	}
	return Complex (
		((a.x*b.x) + (a.y*b.y)) / (b.x*b.x + b.y*b.y),
		((a.y*b.x) - (a.x*b.y)) / (b.x*b.x + b.y*b.y)
	);
}

std::ostream& operator<<(std::ostream& out, const Complex& a)
{
	if (a.y >= 0) {
		out << a.x << '+' << a.y << 'i' << std::endl;
		return out;
	}
	out << a.x  << a.y << 'i' << std::endl;
    return out;
}
