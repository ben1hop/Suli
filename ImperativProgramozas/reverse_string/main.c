#include "func.h"
#include <stdlib.h>

int main(int argc, char* argv[])
{
    FILE* file;
    if (argc == 1) { // Userinput
        reverse(stdin);
    }
    else if( argc > 1){ // Tobb input
        int i = 1;
        while(i < argc){
            file = fopen(argv[i], "r");
            if (file == NULL) {
                fprintf(stderr, "Nemletezo file nev.");
                i++;
            }else{
                reverse(file);
                fclose(file);
                i++;
            }
        }      
    }   
    fclose(file);
    return 0;
}