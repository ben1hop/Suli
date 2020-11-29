#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 30

typedef struct {
    char* name;
    int age;
    char* residence;
} Adatok;

void init_tomb(Adatok* tomb) { // memoriat foglal a tombben a stringeknek
    for (int i = 0; i < MAX_SIZE; i++) {
        tomb[i].name = (char*)malloc(sizeof(char*));
        tomb[i].residence = (char*)malloc(sizeof(char*));
    }
}

void create_file(FILE* file) { // elkesziti a filet
    file = fopen("students.txt", "w");
    fprintf(file, "3\nJack 30 Valentine\nJohn 45 BlackWater\nArthur 48 Rhodes");
    fclose(file);
}

int read_file(FILE* file, Adatok* tomb) { // beolvassa a filet a tombbe
    file = fopen("students.txt", "r");

    int line;
    fscanf(file, "%d", &line);

    for (int i = 0; i < line; i++) 
    {
        fscanf(file, "%s", tomb[i].name);
        fscanf(file, "%d", &tomb[i].age);
        fscanf(file, "%s", tomb[i].residence);
    }

    fclose(file);
    return line;
}

int kezdobetu(Adatok* tomb, int sorok) { // megszamolja a kezdobetusoket
    int counter=0;
    for (int i = 0; i < sorok; i++) {
        if (tomb[i].name[0] == 'J') {
            counter++;
        }
    }
    return counter;
}

void csere(Adatok* tomb, int sorok) { // cserel a feltetel alapjan

    char* buff = (char*)malloc(sizeof(char*));

    for (int i = 0; i < sorok - 1; i++) {
        if (strchr(tomb[i].name, 'a') != NULL) {
            int j = i+1;
            while (j < sorok && (strchr(tomb[j].name, 'a') == NULL && strchr(tomb[j].name, 'A') == NULL)) {
                j++;
            }
            if (j < sorok) {
                strcpy(buff, tomb[i].name);
                strcpy(tomb[i].name, tomb[j].name);
                strcpy(tomb[j].name, buff);
            }
     
        }
    }
    free(buff); /*Ez visual studioban valamiert lehal*/
}

char* leghosszabb_nev(Adatok* tomb, int sorok) { // megadja a leghosszabb cimhez tartozo nevet
    int max = 0;
    int hossz;
    char* nev= NULL;
    for (int i = 0; i < sorok; i++) {
        hossz = strlen(tomb[i].residence);
        if (hossz > max) {
            max = hossz;
            nev = tomb[i].name;
        }
    }
    return nev;
}

void nevek(Adatok* tomb, int sorok) {
    for (int i = 0; i < sorok; i++) {
        printf("%s\n", tomb[i].name);
    }
}

/*GCC lewarningolja ha a metodusokba &tomb-ot teszek be*/

int main()
{
    FILE* file = NULL;
    Adatok tomb[MAX_SIZE];
    init_tomb(tomb);

    create_file(file);

    int sorok = read_file(file, tomb);

    printf("%d ember neve kezdodik 'J'-vel.\n", kezdobetu(tomb, sorok));

    printf("A nevek sorrendje:\n");
    nevek(tomb, sorok);

    csere(tomb, sorok);

    printf("A nevek sorrendje csere utan:\n");
    nevek(tomb, sorok);

    printf("A leghosszabb telepulesneven elo: %s\n", leghosszabb_nev(tomb, sorok));

    free(file);
}