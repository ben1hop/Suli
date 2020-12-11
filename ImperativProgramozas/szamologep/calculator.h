#ifndef CALCULATOR_H
#define CALCULATOR_H

#include <stdio.h>

typedef struct CALC {
	enum States* state;
	int memory;
	int input;
} calculator;

int digit(calculator* calc, int n);

int plus(calculator* calc);

int times(calculator* calc);

int reset(calculator* calc);

#endif