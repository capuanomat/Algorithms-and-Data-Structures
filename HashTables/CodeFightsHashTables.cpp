

class CodeFightsHashTables {
public:

    /**
     * PROBLEM: groupingDishes
     *
     * DESCRIPTION: You have a list of dishes. Each dish is associated with a list of ingredients
     *              used to prepare it. You want to group the dishes by ingredients, so that for
     *              each ingredient you'll be able to find all the dishes that contain it (if there
     *              are at least 2 such dishes).
     *              Return an array where each element is a list with the first element equal to
     *              the name of the ingredient and all of the other elements equal to the names of
     *              dishes that contain this ingredient. The dishes inside each list should be
     *              sorted lexicographically. The result array should be sorted lexicographically
     *              by the names of the ingredients in its elements.
     */
    vector<vector<string>> groupingDishes(vector<vector<string>> dishes) {

        std::map<std::string, std::set<std::string>> dishMap;
        for (int i = 0; i < dishes.size(); i++) {
            std::string dish = dishes[i][0];
            for (int j = 1; j < dishes[i].size(); j++) {
                std::string ingredient = dishes[i][j];
                // Check if the dish is not already in the map, if it isn't then create new set for it
                if (dishMap.find(ingredient) == dishMap.end()) {
                    dishMap[ingredient] = {};
                }
                dishMap[ingredient].insert(dish);
            }
        }

        // Convert the map back to a 2D vector
        vector<vector<string>> output;
        std::map<std::string, std::set<std::string>>::iterator it;
        int i = 0;
        for (it = dishMap.begin(); it != dishMap.end(); it++) {
            std::string ingredient = it->first;
            std::set<std::string> dishes = it->second;
            if (dishes.size() > 1) {
                std::cout << ingredient << " - " << dishes.size() << std::endl;
                // This ingredient has at least two dishes, create a vector to store them in output, and add ingredient
                std::vector<string> dishesVec;
                dishesVec.push_back(ingredient);
                for (std::string dish: dishes) {
                    std::cout << dish << ", ";
                    dishesVec.push_back(dish);
                }
                std::cout << std::endl;
                output.push_back(dishesVec);
                i++;
            }
        }

        return output;
    }
};

int main() {
    std::cout << "----- Starting the main function: CodeFightsHashTables (C++) -----" << std::endl;
    return 0;
}
