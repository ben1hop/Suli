#include <iostream>
#include "./Complex.h"

using namespace std;

Complex read_number() {
    float x, y;
    Complex a;  
    cout << "\nx=";
    cin >> x;
    cout << "y=";
    cin >> y;
    a = Complex(x, y);
    return a;
}

int main() {
    cout << "Komplex kalkulator: \n" << "1 - Osszeadas\n2 - Kivonas\n3 - Szorzas\n4 - Osztas\n5 - Kilepes\n";
    int n;
    cin >> n;

    Complex a, b, c;

    while (n != 5) {
        switch (n) {
            case 1:
                cout << "Adja meg a ket komplex szamot: \n";
                cout << "Elso: ";
                a = read_number();
                cout << "Masodik: ";
                b = read_number();
                c = a + b;
                cout << "\nEredmeny: " << c;
                break;
            case 2:
                cout << "Adja meg a ket komplex szamot: \n";
                cout << "Elso: ";
                a = read_number();
                cout << "Masodik: ";
                b = read_number();
                c = a - b;
                cout << "\nEredmeny: " << c;
                break;
            case 3:
                cout << "Adja meg a ket komplex szamot: \n";
                cout << "Elso: ";
                a = read_number();
                cout << "Masodik: ";
                b = read_number();
                c = a * b;
                cout << "\nEredmeny: " << c;
                break;
            case 4:
                cout << "Adja meg a ket komplex szamot: \n";
                cout << "Elso: ";
                a = read_number();
                cout << "Masodik: ";
                b = read_number();
                try{
                    c = a / b;
                }
                catch (Complex::ERRORS& err) {
                    cout << "Nullaval valo osztas!";
                    break;
                }
                cout << "\nEredmeny: " << c;
                break;
            case 5:
                return 0;
        }
        cout << endl;
        cout << "Kovetkezo muvelet? (1..5)\n";
        cin >> n;
    }
}