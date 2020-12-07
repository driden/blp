package ort.assi.blp.entities;

import ort.assi.blp.secure.SecurityLevel;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectManager {
    private final HashMap<String, SysObject> objects = new HashMap<>();

    public ObjectManager() { }

    public SysObject getObject(String objectName) {
        return objects.get(objectName);
    }

    public void createObject(String name, SecurityLevel level) {
        this.objects.putIfAbsent(name, new SysObject(name, level));
    }

    public Boolean existsObject(String name) {
        return this.objects.containsKey(name);
    }

    public void destroyObject(SysObject object) {
        this.objects.remove(object.getName());
    }
}
