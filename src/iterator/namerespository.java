/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;
import java.util.List;

public class namerespository implements container {

public List<GameObject> colors = new LinkedList<GameObject>();

    public void setColors(List<GameObject> colors) {
        this.colors = colors;
    }
    
    @Override
    public Iterator getIterator() {
        return new nameIterator();
    }

    private class nameIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < colors.size()) {
                return true;
            } else {
                return false;
            }
        }

        public Object next() {
            if (this.hasNext()) {
                return colors.get(index++);
            } else {
                return null;
            }
        }

    }
}
