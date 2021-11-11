class Node {
public:
    double key;
    int balance;
    Node* left;
    Node* right;

    static int nodeCount;

    Node() { left = right = nullptr; balance = 0; }
    Node(double k) { left = right = nullptr; balance = 0; key = k; nodeCount++; }
    ~Node() { nodeCount--; }
};