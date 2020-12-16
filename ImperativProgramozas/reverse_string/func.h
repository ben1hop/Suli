#ifndef FUNC_H
#define FUNC_H

#include <stdio.h>

void reverse(FILE* file);
void init_array(char* lines, int start, int end);
char* read(FILE* file, char* lines, int* lSize);
void write(char* lines, int *lSize);

#endif