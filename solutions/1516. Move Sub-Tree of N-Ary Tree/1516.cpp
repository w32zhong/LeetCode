class Solution {
 public:
  Node* moveSubTree(Node* root, Node* p, Node* q) {
    if (find(begin(q->children), end(q->children), p) != end(q->children))
      return root;

    // Create a dummy Node for the case when root == p
    Node* dummy = new Node(0, {root});

    // Get each parent of p and q
    Node* pParent = getParent(dummy, p);
    Node* qParent = getParent(p, q);

    // Get p's original index in p's parent
    vector<Node*>& pSiblings = pParent->children;
    const int pIndex =
        find(begin(pSiblings), end(pSiblings), p) - begin(pSiblings);
    pSiblings.erase(begin(pSiblings) + pIndex);

    q->children.push_back(p);

    // If q is in the p's subtree, qParent != nullptr
    if (qParent != nullptr) {
      vector<Node*>& qSiblings = qParent->children;
      qSiblings.erase(remove(begin(qSiblings), end(qSiblings), q),
                      end(qSiblings));
      pSiblings.insert(begin(pSiblings) + pIndex, q);
    }

    return dummy->children[0];
  }

 private:
  Node* getParent(Node* root, Node* target) {
    for (Node* child : root->children) {
      if (child == target)
        return root;
      Node* res = getParent(child, target);
      if (res != nullptr)
        return res;
    }
    return nullptr;
  }
};
