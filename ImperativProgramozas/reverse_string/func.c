#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "func.h"

#define INITCAP 8
#define BUFSIZE 1024

#define STARTING_SIZE (INITCAP * BUFSIZE * sizeof(char))


void init_array(char* lines, int start, int end) { // nemkell a garbage data
    for (int i = start; i < end; i++) {
        lines[i] = '\0';
    }

}

void write(char* lines, int* lSize) {
    // Vegiglepkedunk a a foglalt memoria terulet utolso elemetol visszafele es kiirjuk a nem ures es newline karaktereket
    int j;
    for (int i = *lSize; i > 0; --i) {
        j = 1;
        printf("%d ", i);
        int curr_ch;
        while (j <= BUFSIZE) {
            curr_ch = lines[((i * BUFSIZE)) - j];
            if (curr_ch != '\n' && curr_ch != '\0') { // A file enterre kell vegzodjon
                printf("%c", lines[((i * BUFSIZE)) - j]);
            }
            j++;
        }
        printf("\n");
    }
}

char* read(FILE* file, char* lines, int* lSize) {

    int line_counter = INITCAP;
    char buff[BUFSIZE] = { '\0' };

    while (fgets(buff, sizeof(buff), file) != NULL) {

        // a sor karaktereinek elementese a megfelelo memoria cimken
        for (int i = 0; i < strlen(buff) - 1; i++) { // valamiert csak -1ig megyunk
            lines[(i + (*lSize * BUFSIZE))] = buff[i];
        }
        

        *lSize += 1;
        // Ha tullepnenk a memoria hatart
        if (*lSize == line_counter) {          
            lines = (char*)realloc(lines, (line_counter*2) * BUFSIZE * sizeof(char));
            init_array(lines, (line_counter*BUFSIZE) , ((line_counter * 2)*BUFSIZE)); // initalizing the new block of memory
            line_counter *= 2;

        }
    }
    return lines;
}


void reverse(FILE* file) { // ennek a parameternek semmi ertelme raadasul definialva sincs h miert kene kulon funkcionak lennie
    char* lines = (char*)malloc(STARTING_SIZE);
    if (lines == NULL) {
        fprintf(stderr, "Tarolo tomb foglalasi hiba.");
        exit(-1);
    }
    init_array(lines,0,STARTING_SIZE);
    int lSize = 0;
    write(read(file, lines, &lSize), &lSize);
    free(lines);
}