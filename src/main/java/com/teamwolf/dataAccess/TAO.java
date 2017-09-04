package com.teamwolf.dataAccess;

import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;

import java.io.*;

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

    public T getByUnique(Serializable id) {
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

    public T update(T o) {
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

}
