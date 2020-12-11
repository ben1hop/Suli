#include <stdlib.h>
#include "calculator.h"

enum States {
	LEFT = 0,
	LEFT_PLUS,
	LEFT_TIMES,
	RIGHT_PLUS,
	RIGHT_TIMES
}States;

int digit(calculator* calc, int n) {
	calc->input = (10 * calc->input) + n;
	if ((int)*calc->state == LEFT_PLUS)
	{
		*calc->state = RIGHT_PLUS;
	}
	else {
		if ((int)*calc->state == LEFT_TIMES)
		{
			*calc->state = RIGHT_TIMES;
		}
	}
	return calc->input;
}

int plus(calculator* calc) {
	if ((int)*calc->state == LEFT) {
		calc->memory = calc->input;
	}
	else {
		if ((int)*calc->state == RIGHT_PLUS) {
			calc->memory = calc->memory + calc->input;
		}
		else {
			if ((int)*calc->state == RIGHT_TIMES) {
				calc->memory = calc->memory * calc->input;
			}
		}
	}
	calc->input = 0;
	*calc->state = LEFT_PLUS;
	return calc->memory;
}

int times(calculator* calc) {
	if ((int)*calc->state == LEFT) {
		calc->memory = calc->input;
	}
	else {
		if ((int)*calc->state == RIGHT_PLUS) {
			calc->memory = calc->memory + calc->input;
		}
		else {
			if ((int)*calc->state == RIGHT_TIMES) {
				calc->memory = calc->memory * calc->input;
			}
		}
	}
	calc->input = 0;
	*calc->state = LEFT_TIMES;
	return calc->memory;
}

int reset(calculator* calc) {
	calc->state = NULL;
	calc->state = malloc(sizeof(int));
	*calc->state = LEFT;
	calc->input = 0;
	calc->memory = 0;
	return calc->input;
}
