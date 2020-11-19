/*Írj egy programot, ami beolvas két egész számot, majd hozz létre két tömböt dinamikusan a megadott méreteknek megfelelően.  Tetszőleges számokkal töltsd fel a tömböket. Az egyserűség kedvéért tegyük fel, hogy a tömbök egyenként nem tartalmaznak duplikátumokat és szigorúan monoton növekvőek.

Egy függvénynek add át ezt a két tömböt és a függvényen belül hozz létre dinamikusan egy 3. tömböt is, amiben a bemeneti paraméterként megkapott tömbök metszetét kell tárolni, majd egy következő függvénynek add át ezt a metszeteket tároló tömböt és jelenítsd meg a standard kimeneten. Gondoskodj a dinamikusan lefoglalt memória helyes felszabadításáról.

Segítség: a 3. tömb mérete a kisebbik tömb méretével lesz egyenlő, hiszen maximum annyi metszete lehet két tömbnek, mint a kisebbik mérete, ha a méretek megegyeznek, akkor irreleváns, hogy melyik tömb méretét használod. */

#include <stdio.h>
#include <stdlib.h>

void showArray(int* tomb, int size) {
	for (int i = 0; i < size; i++) {
		printf("%d ", tomb[i]);
	}
}

void metszet(int* tomb_a, int* tomb_b, int a , int b) {
	int* tomb_c;
	int size;
	if (a <= b) {
		tomb_c = (int*)malloc(a * sizeof(int));
	}
	else {
		tomb_c = (int*)malloc(b * sizeof(int));	
	}
	int c = 0;

	int j;
	for (int i = 0; i < a; i++) {
		j = 0;
		while (j < b && tomb_b[j] != tomb_a[i]){
			j++;
		}
		if (j < b) {
			int x = 0;
			while (x < c && tomb_c[x] != tomb_a[i]) {
				x++;
			}
			if (x == c) {
				tomb_c[c] = tomb_a[i];
				c++;
			}
		}
	}

	showArray(tomb_c, c);
	free(tomb_c);
}

int main() {
	int a, b;
	printf("Adjon meg ket szamot: ");
	scanf("%d%d", &a, &b);

	int* tomb_a = (int*)malloc(a * sizeof(int));
	int* tomb_b = (int*)malloc(b * sizeof(int));

	printf("\nelso tomb elemei: \n");
	for (int i = 0; i < a; i++) {
		printf("\nA/%d:", i+1);
		scanf("%d", &tomb_a[i]);
	}
	printf("masodik tomb elemei: \n");
	for (int i = 0; i < b; i++) {
		printf("\nB/%d:", i + 1);
		scanf("%d", &tomb_b[i]);
	}

	metszet(tomb_a, tomb_b,a,b);
	free(tomb_a);
	free(tomb_b);
}