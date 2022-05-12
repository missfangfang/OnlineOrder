package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Restaurant> query = builder.createQuery(Restaurant.class);
            query.from(Restaurant.class);
            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

//        try (Session session = sessionFactory.openSession()) {
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
//            criteria.from(Restaurant.class);
//            return session.createQuery(criteria).getResultList();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

//        try (Session session = sessionFactory.openSession()) {
//            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
//            if (restaurant != null) {
//                return restaurant.getMenuItemList();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            MenuItem menuItem = session.get(MenuItem.class, menuItemId);
            return menuItem;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

//        try (Session session = sessionFactory.openSession()) {
//            return session.get(MenuItem.class, menuItemId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        return null;
    }
}