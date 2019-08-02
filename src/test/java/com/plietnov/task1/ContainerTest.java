package com.plietnov.task1;

import com.plietnov.task1.entity.LeapTop;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ContainerTest {
    Container<LeapTop> pc;
    public final int numberOfIterAdd = 1000;
    public final int numberOfIterRemove = 500;

    @Before
    public void setStateBeforeTest() {
        pc = new Container<>();
        for (int i = 0; i < numberOfIterAdd; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pc.add(lp);
        }
    }

    @Test
    public void testMethodAdd() {
        assertEquals(numberOfIterAdd, pc.size());
    }

    @Test
    public void testMethodAddByIndex() {
        for (int i = 0; i < numberOfIterAdd; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pc.add(i, lp);
        }
        assertEquals(numberOfIterAdd + numberOfIterAdd, pc.size());

    }

    @Test
    public void testMethodRemoveByIndex() {
        for (int i = 0; i < numberOfIterRemove; i++) {
            pc.remove(0);
        }
        assertEquals((numberOfIterAdd - numberOfIterRemove), pc.size());
    }

    @Test
    public void testRemoveObject() {
        LeapTop lp = new LeapTop("Test");
        lp.setId(2000);
        lp.setType("test");
        lp.setClassification("test");
        lp.setNameOfProduct("test");
        lp.setDescription("test");
        pc.add(lp);
        assertTrue(pc.contains(lp));
        pc.remove(lp);
        assertFalse(pc.contains(lp));
    }

    @Test
    public void testIteratorPredicate() {
        Predicate<LeapTop> p = (o) -> (o.getId() % 2 == 0);
        Iterator<LeapTop> iterator = pc.iterator(p);
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals((numberOfIterAdd - numberOfIterRemove), count);
    }

    @Test
    public void testGetElementFromContainer() {
        int index = pc.size();
        LeapTop lp = new LeapTop("Test");
        lp.setId(2000);
        lp.setType("test");
        lp.setClassification("test");
        lp.setNameOfProduct("test");
        lp.setDescription("Some text");
        assertNotEquals(lp, pc.get(index - 1));
        pc.add(lp);
        assertEquals(lp, pc.get(index));
    }

    @Test
    public void testToArray() {
        LeapTop[] pcTest = new LeapTop[10];
        for (int i = 0; i < 10; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pcTest[i] = lp;
        }
        pcTest = pc.toArray(pcTest);
        var test = pc.toArray();
        pcTest = new LeapTop[10000];
        for (int i = 0; i < 10; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pcTest[i] = lp;
        }
        pcTest = pc.toArray(pcTest);
    }

    @Test
    public void testIsEmpty() {
        assertFalse(pc.isEmpty());
        pc.clear();
        assertTrue(pc.isEmpty());
    }

    @Test
    public void testContainsAll() {
        var pcTest = new Container<LeapTop>();
        for (int i = 0; i < numberOfIterRemove; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pcTest.add(lp);
            pc.add(lp);
        }
        System.out.println(pc.containsAll(pcTest));
        assertTrue(pc.containsAll(pcTest));
    }

    @Test
    public void testAddAll() {
        var pcTest = new Container<LeapTop>();
        for (int i = 0; i < numberOfIterRemove; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pcTest.add(lp);
        }
        pc.clear();
        pc.addAll(pcTest);
        pc.addAll(numberOfIterRemove, pcTest);
    }

    @Test
    public void testRemoveAllAndRetainAll() {
        var pcTest = new Container<LeapTop>();
        for (int i = 0; i < numberOfIterRemove; i++) {
            LeapTop lp = new LeapTop("some PC ==> " + i);
            lp.setId(i);
            lp.setType("type № " + i);
            lp.setClassification("class № " + i);
            lp.setNameOfProduct("name " + i);
            lp.setDescription("Some text" + i);
            pcTest.add(lp);
        }
        pc.clear();
        pc.addAll(pcTest);
        pc.addAll(numberOfIterRemove, pcTest);
        pc.removeAll(pcTest);
        pc.retainAll(pcTest);
    }

    @Test
    public void testLastIndexOf() {
        var pcTest = new LeapTop();
        LeapTop lp = new LeapTop("some PC ==> " + 5);
        lp.setId(5);
        lp.setType("type № " + 5);
        lp.setClassification("class № " + 5);
        lp.setNameOfProduct("name " + 5);
        lp.setDescription("Some text" + 5);
        var testPosition = pc.size();
        pc.add(lp);
        pc.lastIndexOf(pcTest);
        assertTrue(pc.contains(lp));
        assertEquals(testPosition, pc.lastIndexOf(lp));
        pcTest = new LeapTop();
        pcTest.setId(7777);
        pcTest.setType("none");
        pcTest.setClassification("none");
        pcTest.setNameOfProduct("none");
        pcTest.setDescription("none");
        assertEquals(-1, pc.lastIndexOf(pcTest));
        pc.clear();
        assertEquals(-1, pc.lastIndexOf(pcTest));
    }

    @Test
    public void testRetainAll() {
        var lpList = new Container<LeapTop>();
        var lp = new LeapTop("none++11111111");
        lp.setType("some11111111111");
        lp.setNameOfProduct("some Stuff111111111111");
        lp.setClassification("some class1111111111111");
        lp.setId(5000);
        lpList.add(lp);
        pc.add(lp);
        pc.retainAll(lpList);
        pc.toString();
        assertEquals(1, pc.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddException() {
        var lp = new LeapTop("none++11111111");
        lp.setType("some11111111111");
        lp.setNameOfProduct("some Stuff111111111111");
        lp.setClassification("some class1111111111111");
        lp.setId(5000);
        pc.add(pc.size() * numberOfIterAdd, lp);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllException() {
        var lpList = new Container<LeapTop>();
        var lp = new LeapTop("none++11111111");
        lp.setType("some11111111111");
        lp.setNameOfProduct("some Stuff111111111111");
        lp.setClassification("some class1111111111111");
        lp.setId(5000);
        lpList.add(lp);
        pc.addAll(pc.size() * numberOfIterAdd, lpList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIndexException() {
        System.out.println(pc.get(pc.size() * numberOfIterAdd));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetByIndexException() {
        var lp = new LeapTop("none++11111111");
        lp.setType("some11111111111");
        lp.setNameOfProduct("some Stuff111111111111");
        lp.setClassification("some class1111111111111");
        lp.setId(5000);
        System.out.println(pc.set(pc.size() * numberOfIterAdd, lp));
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextException() {
        var itr = pc.iterator();
        while (true) {
            itr.next();
        }
    }

    @Test
    public void testRemove() {
        var lp = new LeapTop("none++11111111");
        lp.setType("some11111111111");
        lp.setNameOfProduct("some Stuff111111111111");
        lp.setClassification("some class1111111111111");
        lp.setId(5000);
        assertFalse(pc.remove(lp));
    }

    @Test
    public void testAddAllEmplyList() {
        var lpList = new Container<LeapTop>();
        assertFalse(pc.addAll(lpList));
        assertFalse(pc.addAll(null));
    }

    @Test
    public void testAddAllByIndexEmplyList() {
        var lpList = new Container<LeapTop>();
        assertFalse(pc.addAll(0, lpList));
        assertFalse(pc.addAll(0, null));
    }

    @Test
    public void testSet() {
        var lp = new LeapTop("none++11111111");
        lp.setType("some11111111111");
        lp.setNameOfProduct("some Stuff111111111111");
        lp.setClassification("some class1111111111111");
        lp.setId(5000);
        pc.set(0, lp);
        assertEquals((pc.set(0, lp)), lp);
    }

    @Test
    public void testLastIndexOfNull() {
        pc.set(numberOfIterRemove, null);
        assertEquals(numberOfIterRemove, pc.lastIndexOf(null));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationsItr() {
        pc.listIterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationsItrIndex() {
        pc.listIterator(numberOfIterRemove);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationsSubList() {
        pc.subList(numberOfIterRemove, numberOfIterRemove);
    }
}
