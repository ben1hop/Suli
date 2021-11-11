#include <string>
#include "Node.hpp"
#include <queue>
#include <sstream>
#include <cmath>
#include <iomanip>

using namespace std;

class AVLTree {
private:
    AVLTree(){}
public:

#pragma region Callable methods

    #pragma region Insert
    static void AVLInsert(Node*& t, double k, bool& d) {
        if (t == nullptr) {
            t = new Node(k);
            d = true;
        }
        else {
            if (k < t->key) {
                AVLInsert(t->left, k, d);
                if (d) {
                    leftSubTreeGrown(t, d);
                }
            }
            else if (k > t->key) {
                AVLInsert(t->right, k, d);
                if (d) {
                    rightSubTreeGrown(t, d);
                }
            }
            else {
                d = false;
            }
        }
    }
#pragma endregion

    #pragma region Traversal
    // stackoverflows szintbejárás ami tudja kezelni az üres ágakat is
    static string levelOrderWithEmpty(Node* t, int treeHeight) {
        stringstream ss;
        int level = 1;
        //treeHeight >= level &&
        while (printLevel(t, level, ss)) {
            level++;
            ss << "|";
        }
        return ss.str();
    }

    // algo1-es szintbejáró algoritmus , probléma hogy nem tudja kezelni az üres ágakat kiiratáshoz így nem egyértelmű array formában
    static string levelOrder(Node* t) {
        stringstream ss;
        //for (int k = 0; k < (2 ^ avlTree.height) + 1; k++) {

        //}
        //char out[(2 ^ avlTree.height) + 1];
        int i = 0;
        if (t != nullptr) {
            queue<Node*> Q;
            Q.push(t);
            while (!Q.empty()) {
                Node* s = Q.front();
                Q.pop();

                ss << s->key;
                i++;
                if (ceil(log2(i + 1) == floor(log2(i + 1)))) {
                    ss << "|";
                }
                if (s->left != nullptr) {
                    Q.push(s->left);
                }
                if (s->right != nullptr) {
                    Q.push(s->right);
                }
            }
        }
        return ss.str();
    }

#pragma endregion

    #pragma region Delete
    static void AVLremMin(Node*& t, Node*& minp, bool& d) {
        // valahogy igy kene kineznie
        //1. minp t alltal mutatott node ertekre mutat
        //2. t helyere bemasoljuk a jobb gyereket igy a szulo rafog mutatni
        //3. t nem mutat jobb gyerekre -> del helyett nullptr
        //4. felszabaditjuk az eredeti t-t amiremostmar a minp mutat

        if (t->left == nullptr) {
            minp = t;
            t = minp->right;
            minp->right = nullptr;
            // delete minp->right; // ez igy nemjo mert felszabaditja a t jobbot amit eppen tovabb adtunk hely szerint a szulo balnak
            // viszont igy a root deletenel nemlesz jo mivel a minp erteket megkene orizni --> megoldas a sima remMin a Set-ben hivas utan deleteljuk a minp-t
            // delete minp;
            d = true;
        }
        else {
            AVLremMin(t->left, minp, d);
            if (d)
                leftSubTreeShrunk(t, d);
        }
    }

    static void AVLremMax(Node*& t, Node*& maxp, bool& d) {
        if (t->right == nullptr) {
            maxp = t;
            t = maxp->left;
            maxp->left = nullptr;
            //minp->right = nullptr;
            delete maxp; 
            d = true;
        }
        else {
            AVLremMax(t->right, maxp, d);
            if (d)
                rightSubTreeShrunk(t, d);
        }
    }

    static void AVLdel(Node*& t, double k, bool& d) {
        if (t != nullptr) {
            if (k < t->key) {
                AVLdel(t->left, k, d);
                if (d)
                    leftSubTreeShrunk(t, d);
            }
            else if (k > t->key) {
                AVLdel(t->right, k, d);
                if (d)
                    rightSubTreeShrunk(t, d);
            }
            else if (k = t->key) {
                AVLdelRoot(t, d);
            }
            else {
                d = false;
            }
        }
    }
#pragma endregion

    #pragma region Find
    static void AVLFind(Node* const& t, double value, bool& found) {
        if (t != nullptr) {
            if (t->key == value) {
                found = true;
            }
            else if (t->key > value) {
                AVLFind(t->left, value, found);
            }
            else if (t->key < value) {
                AVLFind(t->right, value, found);
            }
        }
    }
    #pragma endregion

#pragma endregion
    
private:

#pragma region Helper methods

    #pragma region Traversal
    static bool printLevel(Node* root, int level, stringstream& ss)
    {
        if (root == nullptr) {
            // level szinten rosszul tolti az ures helyeket ha magassag-1 szinten mar null a szulo
            //for (int i = 0; i < level; i++) { // ezert ha ilyen eset van akkor eleg duplazni is az ures karaktert mivel sehogy sem lehet egy egy level a magassagtol 1-nel tavolabb
                //ss << "_";
            //}
            level == 1? ss << "_" : ss << "__";
            return false;
        }
        if (level == 1)
        {
            ss << '.' << root->key << '.';
            return true;
        }
        bool left = printLevel(root->left, level - 1, ss);
        bool right = printLevel(root->right, level - 1, ss);
        return left || right;
    }
#pragma endregion

    #pragma region RemMin Helper Method
        static void leftSubTreeShrunk(Node*& t, bool& d) {
            if (t->balance == 1) {
                balance_PP(t, d);
            }
            else {
                t->balance = t->balance + 1;
                d = (t->balance == 0);
            }
        }

        static void balance_PP(Node*& t, bool& d) {
            Node* r = t->right;
            if (r->balance == -1) {
                balancePPm(t, r);
            }
            else if (r->balance == 0) {
                balancePP0(t, r);
                d = false;
            }
            else if (r->balance == 1) {
                balancePPp(t, r);
            }
        }

        static void balancePP0(Node*& t, Node*& r) {
            t->right = r->left;
            r->left = t;
            t->balance = 1;
            r->balance = -1;
            t = r;
        }
    #pragma endregion

    #pragma region RemMax Helper Methods
        static void AVLdelRoot(Node*& t, bool& d) {
            if (t->left == nullptr) {
                Node* p = t;
                t = p->right;
                delete p;
                d = true;
            }
            else if (t->right == nullptr) {
                Node* p = t;
                t = p->left;
                delete p;
                d = true;
            }
            else if (t->left != nullptr && t->right != nullptr) {
                rightSubTreeMinToRoot(t, d);
                if (d)
                    rightSubTreeShrunk(t, d);
            }
        }

        static void rightSubTreeShrunk(Node*& t, bool& d) {
            if (t->balance == -1) {
                balance_MM(t, d);
            }
            else {
                t->balance = t->balance - 1;
                d = (t->balance == 0);
            }
        }

        static void balance_MM(Node*& t, bool& d) {
            Node* l = t->left;
            if (l->balance == 1) {
                balanceMMp(t, l);
            }
            else if (l->balance == 0) {
                balanceMM0(t, l);
                d = false;
            }
            else if (l->balance == -1) {
                balanceMMm(t, l);
            }
        }

        static void balanceMM0(Node*& t, Node*& l) {
            t->left = l->right;
            l->right = t;
            t->balance = -1;
            l->balance = +1;
            t = l;
        }

        static void rightSubTreeMinToRoot(Node*& t, bool& d) {
            //Node* rem = t;
            Node* p; // itt nem kell egyenloseg
            AVLremMin(t->right, p, d);
            p->left = t->left;
            p->right = t->right;
            p->balance = t->balance;
            // de ez meg kell ide
            t->left = nullptr;
            t->right = nullptr;
            delete t;
            t = p; 
            
        }

    #pragma endregion

    #pragma region Rotations


        static void leftSubTreeGrown(Node*& t, bool& d) {
            if (t->balance == -1) {
                Node* l = t->left;
                if (l->balance == -1) {
                    balanceMMm(t, l);
                }
                else {
                    balanceMMp(t, l);
                }
                d = false;
            }
            else {
                t->balance = t->balance - 1;
                d = (t->balance < 0);
            }
        }

        static void rightSubTreeGrown(Node*& t, bool& d) {
            if (t->balance == 1) {
                Node* r = t->right;
                if (r->balance == 1) {
                    balancePPp(t, r);
                }
                else {
                    balancePPm(t, r);
                }
                d = false;
            }
            else {
                t->balance = t->balance + 1;
                d = (t->balance > 0);
            }
        }

        static void balancePPp(Node*& t, Node*& r) {
            t->right = r->left;
            r->left = t;
            r->balance = t->balance = 0;
            t = r;
        }

        static void balanceMMm(Node*& t, Node*& l) {
            t->left = l->right;
            l->right = t;
            l->balance = t->balance = 0;
            t = l;
        }

        static void balancePPm(Node*& t, Node*& r) {
            Node* l = r->left;
            t->right = l->left;
            r->left = l->right;
            l->left = t;
            l->right = r;
            t->balance = -((l->balance + 1) / 2);
            r->balance = -((1 - l->balance) / 2);
            l->balance = 0;
            t = l;
        }
        static void balanceMMp(Node*& t, Node*& l) {
            Node* r = l->right;
            l->right = r->left;
            t->left = r->right;
            r->left = l;
            r->right = t;
            l->balance = -((r->balance + 1) / 2);
            t->balance = -((1 - r->balance) / 2);
            r->balance = 0;
            t = r;
        }


    #pragma endregion

#pragma endregion

};
