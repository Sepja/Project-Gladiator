package de.twins.ui;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Updates and renders all game objects
 *
 * @author Pierre
 */
public class GameObjectHandler {

    private List<GameObject> gameObjects = new ArrayList<>();



    public void tick() {
//        for (GameObject gameObject : getGameObjects()) {
//            gameObject.tick();
//    }
    }



    public void render(Graphics g) {
        for (GameObject gameObject : getGameObjects()) {
            gameObject.render(g);
        }
    }

    public void addObjects(GameObject... objects) {
        addObjects(Arrays.stream(objects).collect(Collectors.toList()));
    }

    public void addObjects(Collection<GameObject> objects) {
        if (objects != null && !objects.isEmpty()) {
            gameObjects.addAll(objects.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
    }

    public void removeObjects(GameObject... objects) {
        for (GameObject gameObject : objects) {
            getGameObjects().remove(gameObject);
        }
    }

    public List<GameObject> getGameObjects() {
        //ohne Kopie treten permanent ConcurrentModificationErrors auf, sollten weitere GameObjects
        //hinzugefügt werden
        return new ArrayList<>(gameObjects);
    }

}
