package com.teamwolf.dataAccess;

import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.*;

import java.io.*;
import java.util.*;

public class TAO<T extends DataObject>
{
    private SessionFactory sessionFactory;
    private Class<? extends Serializable> classOF;
    //private How<T> how;

    static private SessionFactory badProgramming;

    static // this is not good programming //TODO depency injection?
    {
        //Create the configuration object
        Configuration config = new Configuration();
        //pass our hibernate configuration to the Configuration
        config.configure("hibernate.cfg.xml");

        //Register the configuration as a standard service
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
        badProgramming = config.buildSessionFactory(serviceRegistry);
    }
    private static SessionFactory getSF() { return badProgramming; }

    public TAO(T generic)
    {
        this.sessionFactory = TAO.getSF();
        this.classOF = generic.getClass();
    }
    protected Session getSession()
    {
        return sessionFactory.openSession();
    }//

    public T getById(Serializable id)
    {
        Session con = this.getSession();
        try{
            return (T) con.get(this.classOF, id);
        }
        catch(HibernateException ex)
        {
            ex.printStackTrace();
        }finally{
            con.close();
        }
        return null;
    }

    public Collection<T> getBy(String key,Object value)
    {
        Session con = this.getSession();
        List<T> ret = null;
        try{
            Criteria c = con.createCriteria(classOF);
            c.add(Restrictions.eq(key,value));

            ret = c.list();

        }
        catch(HibernateException ex)
        {
            ex.printStackTrace();
        }finally{
            con.close();
        }
        return null;
    }

    public T update(T o)
    {
        Session s = this.getSession();
        Transaction t =  null;

        try{
            t = s.beginTransaction();
            //a is persistent
            //o is detached
            T a = (T) s.get(this.classOF, o.getID() );
            if(a != null){
                s.merge(o);
                s.save(a);
            }
            t.commit();

        }catch (HibernateException hex){
            hex.printStackTrace();
            t.rollback();
        }finally{
            s.close();
        }
        return o;
    }

    public Collection<T> updateAll(Collection<T> detList)
    {
        Session s = this.getSession();
        Transaction t =  null;

        try{
            t = s.beginTransaction();
            for (T o: detList)
            {
                s.update(o);
            }
            t.commit();

        }catch (HibernateException hex){
            hex.printStackTrace();
            t.rollback();
        }finally{
            s.close();
        }
        return detList;
    }

    public T add(T o)
    {
        if(o.getID() != null)
            throw new RuntimeException("o must return Null for its ID to be added");

        Session s = this.getSession();
        Transaction t =  null;
        Serializable x = null;
        try{
            t = s.beginTransaction();
            //a is persistent
            //o is detached
            x = s.save(o);
            t.commit();

        }catch (HibernateException hex){
            hex.printStackTrace();
            t.rollback();
        }finally{
            s.close();
        }


        return this.getById(x);
    }

    public T delete(T o)
    {
        Session s = this.getSession();
        Transaction t =  null;

        try{
            t = s.beginTransaction();
            //a is persistent
            //o is detached
            T a = (T) s.get(this.classOF, o.getID() );
            if(a != null){
                s.merge(o);
                s.delete(a);
            }
            t.commit();

        }catch (HibernateException hex){
            hex.printStackTrace();
            t.rollback();
        }finally{
            s.close();
        }
        return o;
    }

}
