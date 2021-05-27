
class HashTable {
  /*
     Implement a HashTable, from scratch, without using any builtin associative containers.
     - For examlpe, allow ourselves to use Array<>, ArrayList<>  but not Hash<> Map<> Set<> HashMap<>
     - string key and string value
     - API below
     - Assume you're given a function to "hash" (computationally) a string into an int64
     - Basically behave the same as HashMap<String, String>
  */

  // Option 1: If use array (specify length) --> Need scaling factor, update every time.
  // Option 2: ArrayList --> No need for scaling concerns (maybe less space optimal)

  int entries;

  String[] backingArr;

  double scalingFactor; // If more than 70% full then resize

  int backArrSize = 10;

  public HashTable() {
    this.backingArr = new String[this.backArrSize];
    this.scalingFactor = 0.7; // Could make this adjustable
    this.entries = 0;
  }

  public void Put(String key, String value) {
    // 1. Get hashing value
    int hashValue = Hasher(key);

    // 2. Do we need to scale
    int newNumEntries = entries + 1;
    double newScalingFactor = newNumEntries / backArraSize;
    if (newScalingFactor > scalingFactor) {
      // Resize array ... TODO
    }

    // 3. Modulo so it fits within the Array/ArrayList
    int hashedValue = hashValue % this.backArrSize;

    // 4. Collisions: Linear probing (easiest, could do quadratic or more fancy stuff)

  }

  private int getTrueIndex(int hashedValue) {
    int i = hashedValue;
    while (backingArr[i] != null) {
      i++; // i = (i + 1) % backingArrSize;
    }
    return i;
  }

  public String Get(String key) {
    // Get the entry
    String toReturn = this.backingArr[Hasher...key
   // Only iterate table length

  }

  public static int Hasher(String s) {
    // Returns a cryptographically secure hash of 's' as a 64-bit value
    return SHA1(s);
  }

}

class Unittest {
  static void test1() {
    HashTable ht();
    ht.Put("Foo", "1");
    ht.Put("Bar", "2");
    ht.Put("Cat", "3");
    // [("1", 0), ("2", 1), ("3", 2), null, ... ]
    ht.Get("Bar"); // should return "2"
    ht.Get("Dog"); // should throw or return null, etc.? ("Not Found")
  }
}
