package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.hibernate.HibernateException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        try {
        SessionFactoryUtil.getSessionFactory().openSession();
        } catch(HibernateException e) {
            System.out.printf(e.getMessage());
        }

    }
}