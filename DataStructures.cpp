/**
 * Created by Matthieu J. Capuano on 1/06/2021.
 */

#include <iostream>
#include <vector>
#include <stack>
#include <list>
#include <queue>
#include <priority_queue>
#include <map>
#include <unordered_map>
#include <set>
#include <unordered_set>


void showlist(std::list<std::string> g) {
    std::list<std::string>::iterator it;
    for(it = g.begin(); it != g.end(); ++it)
        std::cout << *it << std::endl;
    std::cout << "---" << std::endl;
}

int main() {

    // ===== VECTOR ===== //
    std::cout << "\n===== VECTOR =====" << std::endl;
    std::vector<std::string> vec2{"this", "is", "another", "test"};
    vec2[2] = "potato";
    for (int i = 0; i < vec2.size(); i++) {
        std::cout << vec2[i] << std::endl;
    }
    // To insert at a specific index
    vec2.insert(vec2.begin() + 2, {"a", "cool"});
    vec2[2] = "potato";
    for (int i = 0; i < vec2.size(); i++) {
        std::cout << vec2[i] << std::endl;
    }


    // ===== LINKED LIST ===== //
    std::cout << "\n===== LINKED LIST =====" << std::endl;
    std::list<std::string> linkedList;
    linkedList.push_back("This");
    linkedList.push_back("is");
    linkedList.push_back("a");
    linkedList.push_back("test");
    linkedList.push_front("FIRST ENTRY BABYYY!");
    linkedList.push_front("NO I'M THE FIRST ENTRY!");
    showlist(linkedList);
    linkedList.pop_front();
    showlist(linkedList);
    linkedList.pop_back();
    showlist(linkedList);
    linkedList.reverse();
    showlist(linkedList);
    linkedList.sort();
    showlist(linkedList);


    // ===== STACK ===== //
    // Key operations: push(...), pop(...), top(...), size(), empty()
    std::cout << "\n===== STACK =====" << std::endl;
    std::stack<std::string> stack;
    stack.push("this");
    stack.push("is");
    stack.push("a");
    stack.push("test");
    while (!stack.empty()) {
        std::cout << stack.top() << std::endl;
        stack.pop();
    }


    // ===== QUEUE ===== //
    std::cout << "\n===== QUEUE =====" << std::endl;
    std::queue<std::string> myQueue;
    myQueue.push("this");
    myQueue.push("is");
    myQueue.push("a");
    myQueue.push("test");
    while (!myQueue.empty()) {
        std::cout << myQueue.front() << std::endl;
        myQueue.pop();
    }

    // ===== PRIORITY QUEUE ===== //



    // Ordered map, sorted by key, add/access is O(log(n)), red-black tree
    std::map<std::string, int> orderedMap;


    // Unordered map, not sorted, add/access is O(1), regular hashtable/hashmap
    std::unordered_map<std::string, int> umap;

    // inserting values by using [] operator
    umap["GeeksforGeeks"] = 10;
    umap["Practice"] = 20;
    umap["Contribute"] = 30;


    std::set<std::string> orderedSet;


    std::unordered_set<std::string> unorderedSet;


    // Allows repeated items, keeps them sorted too
    std::multiset<std::string> multiSet;


    return 0;
}
