#include <stdio.h>

void collatz(int elem, int* hossz) {
	if (elem == 1 && *hossz != 1) {
		printf("%d", elem);
		return;
	}
	if (elem % 2 == 1) {
		*hossz += 1;
		printf("%d,", elem);
		collatz(((elem * 3) + 1), hossz);
	}
	else {
		*hossz += 1;
		printf("%d,", elem);
		collatz(elem / 2, hossz);
	}
}

int main() {
	int first_elem, hossz;
	printf("Adjon meg egy kezdoerteket: ");
	scanf("%d", &first_elem);
	hossz=1;
	collatz(first_elem, &hossz);

	printf("\n%d", hossz);
}