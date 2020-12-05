package ort.assi.blp.entities;

import java.util.HashSet;
import java.util.Optional;

public class ObjectManager {
    private HashSet<SysObject> objects = new HashSet<>();
    private static ObjectManager instance = null;

    private ObjectManager() {
    }

    public static ObjectManager getInstance() {
        if (instance == null) instance = new ObjectManager();
        return instance;
    }

    private HashSet<SysObject> getObjects() {
        return objects;
    }

    private void setObjects(HashSet<SysObject> objects) {
        this.objects = objects;
    }

    private Integer readObject(String objectName) throws Exception {
        Optional<SysObject> obj = findObjectWithName(objectName);

        if (obj.isEmpty())
            throw new Exception(String.format("there's no object with name %s", objectName));

        return obj.get().getValue();
    }

    private Optional<SysObject> findObjectWithName(String objectName) {
        return objects
                .stream()
                .filter(obj -> obj.getName().equals(objectName))
                .findFirst();
    }

    private void writeObject(String objectName, Integer objectValue) {
        Optional<SysObject> obj = findObjectWithName(objectName);
        if (obj.isEmpty()) {
            SysObject object = new SysObject(objectName);
            object.setValue(objectValue);
            this.objects.add(object);
        } else {
            obj.get().setValue(objectValue);
        }
    }
}
