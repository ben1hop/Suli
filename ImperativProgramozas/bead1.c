#include <stdio.h>
#include <stdlib.h>

#define N 10

int main(int argc, char* argv[]) {

    int array[N];

    int paros_osszeg = 0;
    int paratlan_szor = 0;

    // Feltoltes paros paratlan valogatas
    for (int i = 0; i < N; i++) {
        scanf("%d", &array[i]);

        if (array[i] % 2 == 0) {
            paros_osszeg += array[i];
        }
        else {
            if (paratlan_szor == 0) {
                paratlan_szor = array[i];
            }
            else {
                paratlan_szor = paratlan_szor * array[i];
            }

        }
    }
    
    //Legtobbet elofordult
    int element;
    int current_max;
    int eddigi_max = 0;
    for (int i = 0; i < N; i++) {
        current_max = 0;
        for (int j = i + 1; j < N; j++) {
            if (array[i] == array[j]) {
                current_max++;
            }
        }
        if (eddigi_max < current_max) {
            eddigi_max = current_max;
            element = array[i];
        }
    }

    // Rendezés
    for (int i = 0; i < N - 1; i++) {
        for (int j = i + 1; j < N; j++) {
            if (array[i] > array[j]) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

    }

    // Kiiratas
    printf("Paros osszeg: %d\n", paros_osszeg);
    printf("Paratlan szorzat: %d\n", paratlan_szor);
    printf("Elemek rendezve: ");
    for (int i = 0; i < N; i++) {
        printf("%d", array[i]);
        printf(" ");
    }
    printf("\nLegtobbet elofordult: %d", element);


}