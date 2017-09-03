package com.teamwolf.dataAccess;

import java.io.*;

public interface How<T extends DataObject>
{
    Serializable getA(T obj);
}
