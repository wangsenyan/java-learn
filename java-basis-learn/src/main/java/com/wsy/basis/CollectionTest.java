package com.wsy.basis;

import java.util.*;

/**
 * Collection
 *  List是interface
 *   - ArrayList
 *   - LinkedList
 *   - Arrays.asList的返回
 *   - Collections.addAll(collection,11,23,34,45)
 */
public class CollectionTest {
    public static void main(String[] args) {
        Collection<Integer> collection =
                new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        Arrays.asList(1,2,4,577,7);
    }
}
class Snow{}
class Power extends Snow{}
class Light extends Power{}
class Heavy extends Power{}
class Crusty extends Snow{}
class Slush extends Snow{}
class AsListInference{
    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(
                new Crusty(),new Slush(),new Power()
        );
        List<Snow> snows2 = Arrays.asList(
                new Light(),new Heavy()
        );
        List<Snow> snows3 = new ArrayList<Snow>();
        Collections.addAll(snows3,new Light(),new Heavy());
        List<Snow> snows4 = Arrays.<Snow>asList(
                new Light(),new Heavy()
        );
    }
}

class PrintingContainers {
    static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }
    static Map fill(Map<String,String> map){
        map.put("rat","Fuzzy");
        map.put("cat","Rags");
        map.put("dog","Bosco");
        map.put("dog","Spot");
        return map;
    }

    public static void main(String[] args) {
        System.out.println(fill(new ArrayList<String>()));
        System.out.println(fill(new LinkedList<String>()));
        System.out.println(fill(new HashSet<String>()));
        System.out.println(fill(new TreeSet<String>()));
        System.out.println(fill(new LinkedHashSet<String>()));
        System.out.println(fill(new HashMap<String,String>()));
        System.out.println(fill(new TreeMap<String,String>()));
        System.out.println(fill(new LinkedHashMap<String,String>()));
    }
}

class ListIteration {
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<Pet>(Arrays.asList(new Pet("cat"),
                new Pet("dog"),new Pet("rat"),new Pet("pig"),
                new Pet("mouse"),new Pet("ele"),new Pet("turkey"),
                new Pet("chicken")));
        ListIterator<Pet> it = pets.listIterator();
        while (it.hasNext())
            System.out.println(it.next() + "." + it.nextIndex() + ". " +it.previousIndex() + "; ");
        while (it.hasPrevious())
            System.out.println(it.previous());
    }
}

class LinkedListFeatures {
    public static void main(String[] args) {
      LinkedList<Pet> pets = new LinkedList<Pet>(Arrays.asList(new Pet("cat"),
              new Pet("dog"),new Pet("rat"),new Pet("pig"),
              new Pet("mouse"),new Pet("ele"),new Pet("turkey"),
              new Pet("chicken")));
      pets.getFirst();
      pets.element();
      pets.peek();

      pets.remove();
      pets.removeFirst();
      pets.poll();//if pets null,return null
        pets.addFirst(new Pet("duck"));
        pets.offer(new Pet("lion"));
        pets.add(new Pet("tiger"));
        pets.addLast(new Pet("mie"));
    }
}

class SortedSetOfInteger{
    public static void main(String[] args) {
        Random rand = new Random(47);
        SortedSet<Integer> intset = new TreeSet<Integer>();
        for(int i=0;i<10000;i++)
            intset.add(rand.nextInt(30));
        System.out.println(intset);
    }
}