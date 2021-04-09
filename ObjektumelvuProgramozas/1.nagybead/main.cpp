#include "Halmaz.h"
#include <iostream>
#include <string>


#ifdef NORMAL_MODE

class Menu
{
public:
    Menu() { }
    void run();
private:
    Halmaz a;

    static void menuWrite();
    void addElement();
    void removeElement();
    void isEmpty() const { a.empty() ? std::cout << "\nIgen.\n\n" : std::cout << "\nNem.\n\n"; };
    void maxElement() const;
    void writeOut() const { std::cout << std::endl << a << std::endl << std::endl; };

};

void Menu::menuWrite() {
    std::cout << "1.Hozza adas a listahoz\n2.Eltavolitas a listabol\n3.Ures-e a lista?\n4.Lista legnagyobb eleme:\n5.Lista allapota\n6.Kilepes\ninput:";
}

void main() {
    Menu menu;
    menu.run();
}

void Menu::addElement(){
    std::cout << "\nAdjon meg egy integert amit a listaba teszunk:\n";
    int x;
    std::cin >> x; // 2147483647
    std::cout << std::endl;
    a.put(x);
}

void Menu::maxElement() const {
    try {
        std::cout << std::endl << a.maxElement() << "\n\n";
    }
    catch (Halmaz::Exceptions e) {
        if (e == Halmaz::EMPTY_LIST) {
            std::cout << "\nUres listara nincs ertelmezve a muvelet.\n\n";
        }
        else {
            std::cout << "Kezeletlen kivetel.";
        }
    }
}

void Menu::removeElement() {
    std::cout << "\nAdjon meg egy integert amit torlunk a listabol:\n";
    int x;
    std::cin >> x;
    std::cout << std::endl ;
    try {
        a.remove(x);
    }
    catch (Halmaz::Exceptions e) {
        if (e == Halmaz::EMPTY_LIST) {
            std::cout << "\nUres listabol torlunk.\n\n";
        }
        else if (e == Halmaz::NOT_EXISTING) {
            std::cout << "\nNincs ilyen elem a listaban.\n\n";
        }
        else {
            std::cout << "\nKezeletlen kivetel.\n\n";
        }
    }
}

void Menu::run() {
    int i = 0;
    while (i != 6) {
        Menu::menuWrite();
        std::cin >> i;
        switch (i)
        {
        case 1:
            addElement();
            break;
        case 2:
            removeElement();
            break;
        case 3:
            isEmpty();
            break;
        case 4:
            maxElement();
            break;
        case 5:
            writeOut();
            break;
        case 6:
            return;
        default:
            std::cout << "\nIllegalis input.\n\n";
            break;
        }
    }

}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

using namespace std;

TEST_CASE("add", "Text.txt")
{
    const string fileName = "Text.txt";

    ifstream in(fileName.c_str());
    if (in.fail())
    {
        cout << "File name error!" << endl;
        exit(1);
    }

    Halmaz a;
    int x;
    for (int i = 0; i < 5; i++) {
        in >> x;
        a.put(x);
        CHECK(a.benne_van(x) == a.length() - 1); // megtalaljuk-e az utolso helyen
    }
}

TEST_CASE("remove")
{
    Halmaz a;
    a.put(1);
    a.put(2);
    a.put(-5);
    a.put(-1);

    int length = a.length();

    a.remove(1);
    CHECK(a.length() == length-1);
    try {
        a.benne_van(1);
    }
    catch (Halmaz::Exceptions ex) {
        CHECK(ex == Halmaz::NOT_EXISTING);
    }

    try {
        a.remove(9); // nemletezo torles
    }
    catch (Halmaz::Exceptions ex) {
        CHECK(ex == Halmaz::NOT_EXISTING);
    }

    int current_max = a.maxElement();
    a.remove(2); // max torles
    CHECK(a.maxElement() != current_max); // nem a regi max maradt
    CHECK(a.maxElement() == -1); // az uj maxot beallitotta
   
    try {
        a.remove(-5);
        a.remove(-1);
        a.remove(7); // ures listabol torles
    }
    catch (Halmaz::Exceptions ex) {
        CHECK(ex == Halmaz::EMPTY_LIST);
    }
}

TEST_CASE("empty") {
    Halmaz a;
    CHECK(a.empty() == true);
    a.put(1);
    CHECK(a.empty() == false);
    a.remove(1);
    CHECK(a.empty() == true);
}

TEST_CASE("max") {
    Halmaz a;
    try {
        a.maxElement();
    }
    catch (Halmaz::Exceptions ex) {
        CHECK(ex == Halmaz::EMPTY_LIST);
    }
    a.put(2);
    CHECK(a.maxElement() == 2);
    a.put(-1);
    CHECK(a.maxElement() == 2);
}

#endif