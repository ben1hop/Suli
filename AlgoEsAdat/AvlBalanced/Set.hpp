#include "AVLTree.hpp"
#include <string>
#include <cmath>
#include <vector>

using namespace std;

int Node::nodeCount = 0;

class Set {
private:
    Node* min;
    Node* max;
    Node* root;
    vector<string> asArray;
    bool d;
    
public:
   
    Set(const double& rootValue) { 
        Node::nodeCount = 0;
        root = new Node(rootValue);
        max = min = nullptr;
        asArray.resize(1);
        d = false;
    }

    void add(double i) {
        d = false;
        AVLTree::AVLInsert(root, i, d);
    }

    void find(bool& found , double i) {
        AVLTree::AVLFind(root, i, found);
    }

    void remove(double i) {
        d = false;
        AVLTree::AVLdel(root, i, d);
    }

    void remMin() {
        d = false;
        AVLTree::AVLremMin(root,min, d);
        delete min;
    }

    void remMax() {
        d = false;
        AVLTree::AVLremMax(root,max, d);
    }

    void close() {
        // post orderben deletelni kene mindet
        delete root;
    }

    int height() {
        if(Node::nodeCount > 0)
            return floor(1.45 * log2(Node::nodeCount));
        return -1;
    }

    string asString() {
        if (height() > -1) {
            return AVLTree::levelOrderWithEmpty(root, height());
        }
        return "EMPTY";
        
    }
    void print() {
        cout << asString() << endl;
        cout << "Magassag: " << height() << endl;
        cout << "Node szam: " << Node::nodeCount << endl;
    }
};