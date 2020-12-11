#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "calculator.h"


int main(int argc, char* argv[]) {
	calculator calc;

	int i = 1;
	while (i < argc) {
		char* line = argv[i];
		printf("%d\n", reset(&calc));
		for (unsigned int j = 0; j < strlen(line); j++) {
			char current_char = line[j];
			if (current_char == '+')
			{
				printf("%d\n", plus(&calc));
			}
			else if (current_char == '*')
			{
				printf("%d\n", times(&calc));
			}
			else if (current_char < 58 && current_char >= 48)
			{
				printf("%d\n", digit(&calc, current_char - '0'));
			}
		}
		i++;
	}
	free(calc.state);
}