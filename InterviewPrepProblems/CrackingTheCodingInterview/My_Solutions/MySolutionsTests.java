package Cracking_The_Coding_Interview.My_Solutions;

import Cracking_The_Coding_Interview.My_Solutions.Ch3Prob2;

public class MySolutionsTests {

    public static void main(String[] args) {
        System.out.println("***** Executing Tests to My Solutions to Cracking the Coding Interview Problems *****");

        MySolutionsTests tester = new MySolutionsTests();

        System.out.println("--- Chapter 3 Tests ---");
        tester.chapter3Tests();

    }

    private void chapter3Tests() {
        System.out.println("- Problem 2 -");
        chapter3Problem2Test();

        System.out.println("- Problem 6 -");
        chapter3Problem6Test();
    }

    private void chapter3Problem2Test() {
        Ch3Prob2<Integer> minStack = new Ch3Prob2<>();

        int[] entries = {9, 11, 3, 6, 23, 1, 99};

        for (int entry : entries) {
            minStack.push(entry);
            System.out.println(minStack.min());
        }

        System.out.println("--");
        System.out.println(minStack.pop()); // 99
        System.out.println(minStack.min()); // 1
        System.out.println(minStack.pop()); // 1
        System.out.println(minStack.min()); // 3
        System.out.println(minStack.pop()); // 23
        System.out.println(minStack.pop()); // 6
        System.out.println(minStack.min()); // 3
        System.out.println(minStack.pop()); // 3
        System.out.println(minStack.pop()); // 11
        System.out.println(minStack.min()); // 9
    }

    private void chapter3Problem6Test() {
        Ch3Prob6<animal> animalShelterQueue = new Ch3Prob6<>();

        animalShelterQueue.enqueue(new Dog("Dog1"));    // (Dog1)
        animalShelterQueue.enqueue(new Cat("Cat1"));    // (Dog1) -> (Cat1)
        animalShelterQueue.enqueue(new Cat("Cat2"));    // (Dog1) -> (Cat1) -> (Cat2)
        animalShelterQueue.enqueue(new Dog("Dog2"));    // (Dog1) -> (Cat1) -> (Cat2) -> (Dog2)
        animalShelterQueue.enqueue(new Cat("Cat3"));    // (Dog1) -> (Cat1) -> (Cat2) -> (Dog2) -> (Cat3)

        System.out.println(animalShelterQueue.peekAny().species);       // Dog1
        System.out.println(animalShelterQueue.dequeueAny().species);    // Dog1

        System.out.println(animalShelterQueue.peekCat().species);       // Cat1
        System.out.println(animalShelterQueue.peekDog().species);       // Dog2
        System.out.println(animalShelterQueue.dequeueAny().species);    // Cat1

        System.out.println(animalShelterQueue.dequeueCat().species);    // Cat2
        System.out.println(animalShelterQueue.dequeueCat().species);    // Cat3

        System.out.println("--");

        animalShelterQueue.enqueue(new Cat("Cat4"));    // (Dog2) -> (Cat4)
        animalShelterQueue.enqueue(new Cat("Cat5"));    // (Dog2) -> (Cat4) -> (Cat5)
        animalShelterQueue.enqueue(new Cat("Cat6"));    // (Dog2) -> (Cat4) -> (Cat5) -> (Cat6)
        animalShelterQueue.enqueue(new Dog("Dog3"));    // (Dog2) -> (Cat4) -> (Cat5) -> (Cat6) -> (Dog3)

        System.out.println(animalShelterQueue.peekAny().species);       // Dog2
        System.out.println(animalShelterQueue.dequeueAny().species);    // Dog2

        System.out.println(animalShelterQueue.peekCat().species);       // Cat4
        System.out.println(animalShelterQueue.peekDog().species);       // Dog3
        System.out.println("--");
        //System.out.println(animalShelterQueue.dequeueDog().species);    // Dog3
        //System.out.println(animalShelterQueue.dequeueDog().species);    // Should error, no dogs in
    }
}

class animal {
    String species;

    public animal(String species) {
        this.species = species;
    }
}

class Cat extends animal{

    public Cat(String cat) {
        super(cat);
    }
}

class Dog extends animal{

    public Dog(String dog) {
        super(dog);
    }
}