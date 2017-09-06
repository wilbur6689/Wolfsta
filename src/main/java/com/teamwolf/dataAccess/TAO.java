package com.teamwolf.dataAccess;



import java.io.*;
import java.util.*;

public interface TAO<T extends DataObject>
{
    /**
     * gets an object from the database based on the primary key
     * @param id of the desired object
     * @return the object
     */
    T getById(Serializable id);

    /**
     * gets objects by some value, basically a "WHERE key = value"
     * @param key the field, as named in the bean EX: "username" from the User class, or "state" from the CardLookup Class
     * @param value the object that would match the field EX: "cJohnson" for "username" or CardState.DECK for "state"
     * @return a collection of the requested objects
     */
    Collection<T> getBy(String key, Object value);

    /**
     * gets objects by a set of key-value pairs (aka a map)
     * @param compKey this is a map keys are named fields of beans and value the object of the appropriate type
     * @return
     */
    Collection<T> getByCompositeMap(Map<String, Object> compKey);

    /**
     * gets objects by a set of key-value pairs (aka a map)
     * @param compKey MapBuilder is syntactic sugar
     *                you can call this method like so:
     *                TeamDao.getByCompositeMap(map -> { map.put("player1",1); map.put("player2",2);});
     * @return
     */
    Collection<T> getByCompositeMap(MapBuilder compKey);


    /**
     * some of our objects theoretically have composite keys, this method is similar to the above except
     * @param compKey
     * @return
     */
    T getByCompositeKey(Map<String, Object> compKey);
    T getByCompositeKey(MapBuilder compKey);


    /**
     * updates the given object
     * @param o, the object to be updated
     * @return returns the updated object
     */
    T update(T o);

    /**
     * runs the update on all the objects in the collection
     * @param detList
     * @return returns the updated collection
     */
    Collection<T> updateAll(Collection<T> detList);

    /**
     * adds an object to the database, the id field must be null or it will cause an exception
     * @param o, object to be added, id field must be null, non-null field in the database must not be null
     * @return the object, with its assigned id
     */
    T add(T o);

    /**
     * you probably dont want to use this, its not setup for cascading deletes
     * @param o
     * @return
     */
    T delete(T o);

}
