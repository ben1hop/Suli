// Implementation of a set of real numbers using AVL Tree
// Most of the algorithms from : http://aszt.inf.elte.hu/~asvanyi/ad/ad2jegyzet/ad2jegyzetFak.pdf
// Capable of :
// - adding nodes with all 4 types of rotations (++,+)(--,-)(++,-)(--,+)
// - removing nodes from left-right-root
// - min/max removing with additional 2 types of rotations (--,0)(++,0)
// - finding a node
// - printing the tree as an array , with empty nodes aswell


#include <iostream>
#include "Set.hpp"
#include <windows.h>

using namespace std;

int main()
{
    int x;
    //cout << "Adjon meg egy gyoker erteket: "; 
    //cin >> x;

    Set set = Set(5);
    HANDLE h;

    set.add(6);
    set.add(2);
    set.add(3);
    set.add(7);
    set.add(8);
    set.add(1);
    set.add(4);
    set.add(12);
    set.add(0);

    Node* a = new Node(9);
    a->right = new Node(6);
    a->right->left = new Node(5);
    a->right->left->right = new Node(8);
    a->left = new Node(4);
    a->left->left = new Node(2);
    a->left->left->left = new Node(1);
    a->left->left->right = new Node(3);
    a->left->left->right->left = new Node(4);

    //printf("Contains: %s", Set::isBalanced(set.root) ? "true\n" : "false\n");
    printf("Contains: %s", Set::isBalanced(set.root) ? "true\n" : "false\n");
    printf("Contains: %s", Set::isBalanced(a) ? "true\n" : "false\n");

    //delete a->left->left;
    //delete a->left;
    //delete a;
    
    cout << "Navigation:\n1.Add\n2.RemMin\n3.RemMax\n4.Delete\n5.Find\n6.Print\nEscape to exit.\n";
    do {
        h = GetStdHandle(STD_INPUT_HANDLE);
        bool found = false;
        cout << "Navigation: ";
        int x;
        cin >> x;
        switch (x) {
        case 1:
            cout << "Give me a number: ";
            cin >> x;
            cout << endl;
            set.add(x);
            set.print();
            break;
        case 2:
            set.remMin();
            set.print();
            break;
        case 3:
            set.remMax();
            set.print();
            break;
        case 4:
            cout << "Give me a number: ";
            cin >> x;
            cout << endl;
            set.remove(x);
            set.print();
            break;
        case 5:
            found = false;
            cout << "Give me a number: ";
            cin >> x;
            cout << endl;
            set.find(found, x);
            printf("Contains: %s", found ? "true\n" : "false\n");
            break;
        case 6:
            set.print();
            break;
        default:
            cout << "Wrong input\n";
            break;
        }

    } while (GetAsyncKeyState(VK_ESCAPE) == 0);

    set.close();

}
