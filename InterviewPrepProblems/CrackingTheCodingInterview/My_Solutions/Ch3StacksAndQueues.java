package Cracking_The_Coding_Interview.My_Solutions;

public class Ch3StacksAndQueues {

    /**
     * QUESTION 3.1: Three in One
     *
     * DESCRIPTION: Describe how you could use a single array to implement three stacks.
     *
     *
     * HINTS: #2
     *        #12
     *        #38
     *        #58
     *
     * MY SOLUTION: So just thinking about it, implementing a single stack in an array is pretty
     *              straightforward, you just need to specify the size of the array ahead of time
     *              and have either a pointer to the top of the stack or use the size variable to
     *              keep track. Then you can push() in amortized O(1). If you have three stacks,
     *              however, you could either divide the array into three using a constant size
     *              for each stack and index that way (so, for example if the user says he wants
     *              stacks of size 20, then 0-19 are for the first, 20-39 for the second, and 40-59
     *              for the third), or you could let the user input various sizes for the stacks if
     *              he/she knows ahead of time that some will be bigger.
     *
     *              The problem with this is that one stack can fill up and then you have to resize
     *              the whole array (or throw an exception) when some are empty.
     *
     *              I DID NOT IMPLEMENT THIS, just thought it out.
     *
     * OFFICIAL SOLUTION: Approach 1 in the solution is pretty much exactly what I describe in the
     *                    first paragraph of my solution.
     *                    Approach 2 is not thoroughly described, they say that they design the array
     *                    to be circular such that the elements at the end of the last array wrap around
     *                    to the start of the array. They say that when a stack exceeds its initial
     *                    capacity, they grow the allowable capacity and shift the elements as necessary.
     *                    Then they provide a Java code.
     *                    I find this video more helpful: https://www.youtube.com/watch?v=DxW7VAsdX0o&t=45s
     *                    The author mentions that this would have way too much code for an actual
     *                    interview, at most you would be asked to implement certain parts of it.
     *
     * LESSON TO REMEMBER:
     *  1. Remember how to implement two stacks in one array where they start at both ends.
     *  2. Remember the technique for creating N stacks in one array from the video.
     */


    /**
     * QUESTION 3.2: Stack Min
     *
     * DESCRIPTION: How would you design a stack which, in addition to push and pop, has a function min
     *              which returns the minimum element? Push, pop, and min should all operate in O(1) time.
     *
     * HINTS: #27
     *        #59
     *        #78
     *
     * MY SOLUTION: You need to store a node at every entry in the backing array, not just the input
     *              data. In your node, you have the data, and a pointer to the previous minimum. You
     *              also need an instance variable that keeps track of the minimum, you can update it
     *              whenever you push a new minimum or pop a minimum in the array.
     *              CHECK: Ch3Prob2.java
     *
     * OFFICIAL SOLUTION: The minimum doesn't change very often, only when a new minimum is added.
     *                    They essentially just describe the same solution I do but highlight one problem:
     *                    you're wasting a lot of space by storing a variable that keeps track of the
     *                    minimum at every node.
     *                    Their implementation is very different. They actually just create another class
     *                    that extends from the regular Stack class and they overide the push method where
     *                    their push now pushes a node as just described.
     *                    In their more efficient implementation they don't use a nested class, they use
     *                    a second stack and only push values on there when they are equal to or smaller
     *                    than the new min. When they pop, they check if the value is equal and pop it
     *                    from the second stack if yes.
     *
     * LESSONS TO REMEMBER:
     *  1. That you can keep track of the min by having every entry in the stack be a node that points
     *     to the previous minimum. Which is tracked by an instance variable.
     *  2. I was able to do that by rewriting the whole stack implementation (with a nested class for nodes).
     *  3. They did it by extending from the normal stack, overriding the push method (and creating nested class too).
     *  4. This can be made more space efficient by using a second stack that pushes/pops new or equal mins.
     **/

    /**
     * QUESTION 3.3: ...
     */

    /**
     * QUESTION 3.4: ...
     */

    /**
     * QUESTION 3.5: ...
     */

    /**
     * QUESTION 3.6: Animal Shelter
     *
     * DESCRIPTION: An animal shelter, which holds only dogs and cats, operates on a strictly"first in,
     *              first out" basis. People must adopt either the "oldest" (based on arrival time) of
     *              all animals at the shelter, or they can select whether they would prefer a dog or
     *              a cat (and will receive the oldest animal of that type). They cannot select which
     *              specific animal they would like. Create the data structures to maintain this system
     *              and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat.
     *              You may use the built-in Linked list data structure.
     *
     * HINTS: #22
     *        #56
     *        #63
     *
     * MY SOLUTION: MY SOLUTION IS LOGICAL AND A DECENT APPROACH, BUT MORE COMPLEX THAN NECESSARY. THE
     *              IMPLEMENTATION IN Ch3Prob6.java IS ALMOST COMPLETE BUT HAS SOME ERROR.
     *              LOOK THROUGH THE OFFICIAL SOLUTION INSTEAD.
     *              I decided to keep a single queue to keep track of both cats and dogs, instead of having
     *              two separate queues. The idea was to have an overall head and tail pointer, and then
     *              head and tail pointers for both the cats and dogs. Each entry would be a node that points
     *              both to the next element and to the next element of the same animal. For instance, after
     *              adding a dog, two cats, a dog, and a cat, you would have:
     *                  Head 								                           Tail
     *                 c_head         d_head                          c_tail          d_tail
     *              n(nextSame) <-> n(nextSame) <-> n(nextSame) <-> n(nextSame) <-> n(nextSame)
     *              |_______________________________^    |__________^
     *                              |_______________________________________________^
     *              The idea was for the list to be doubly linked so that when you removed the first of an
     *              animal that isn't the first in the list, then you can update the next pointer of the
     *              previous animal (e.g. if you did dequeueDog() in the above the first cat would have to
     *              point directly to the second.
     *              This solution makes sense and is doable, but much more complex than necessary.
     *
     * OFFICIAL SOLUTION: The author says that we can use a single queue but that we would have to iterate
     *                    through the queue to find the first dog or cat (I don't think this is quite accurate,
     *                    if you use pointers like I did in my solution then there's no need for iteration).
     *                    This would increase complexity and decrease efficiency (not decrease efficiency if
     *                    you don't iterate). A simpler and cleaner approach is two have two queues wraped in
     *                    in a class (like the one I made). Then
     *                    -> Store a timestamp to keep track of when each animal is enqueued
     *                    -> When you dequeueAny(), peek at the heads of both lists and return the olter one
     *                    -> Both the Dog and Cat class need to inherit from an (abstract in her implementation)
     *                       animal class so that dequeue can return both dogs and cats (it returns an animal type)
     *                    -> If you wanted, you could use the actual date and time as a timestamp
     *
     * LESSONS TO REMEMBER:
     * 1. A simple solution to this problem is to keep two queues and a timestamp.
     * 2. Then peek at the heads of both during dequeueAny() and return the older one.
     * 3. Her implementation is very good in terms of using abstract/protected/inheritance/etc.
     *    -> LOOK AT THE OFFICIAL SOLUTION, SOME INTERESTING THINGS:
     *    -> She makes the Animal class abstract since it's never instantiated, but Cat and Dog are concrete
     *    -> In the Animal class, the private variable has getters and setters, the others do not.
     *    -> She makes dequeueAny() return an Animal type, the others return a Dog or Cat specifically
     *
     *
     */
}
