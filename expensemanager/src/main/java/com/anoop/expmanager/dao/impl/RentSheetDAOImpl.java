package com.anoop.expmanager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.RentSheetDAO;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class RentSheetDAOImpl implements RentSheetDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<RentSheet> getCurrentMonthRentSheetDetails() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(RentSheet.class);
            criteria.add(Restrictions.eq("month", Util.getCurrentMonth()));
            criteria.add(Restrictions.eq("year", Util.getCurrentYear()));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in getCurrentMonthPaidDetails() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RentSheet> getRentSheetHistory() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(RentSheet.class);
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in getRentPaidHistory() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RentSheet> getRentSheetHistoryPerMonthAndYear(int month, int year) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(RentSheet.class);
            criteria.add(Restrictions.eq("rentGeneratedForMonth", month));
            criteria.add(Restrictions.eq("rentGeneratedForYear", year));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in getRentPaidHistoryPerMonthAndYear() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RentSheet> getRentSheetHistoryPerMonthYearUser(int month, int year, long userId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(RentSheet.class);
            criteria.add(Restrictions.eq("rentGeneratedForMonth", month));
            criteria.add(Restrictions.eq("rentGeneratedForYear", year));
//            criteria.add(Restrictions.eq("user.id", userId));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in getRentSheetHistoryPerMonthYearUser() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void saveRentSheet(RentSheet rentSheet) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(rentSheet);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Caught exception in saveRentPaid() : " + e);
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public RentSheet getLastMonthRentSheetPerUser(long userID) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(RentSheet.class);
            criteria.add(Restrictions.eq("user.id", userID));
//            criteria.setProjection(Projections.max("rentGeneratedDate"));
            criteria.addOrder(Order.desc("rentGeneratedDate"));
            criteria.setMaxResults(1);
            return (RentSheet) criteria.uniqueResult();
        } catch (Exception e) {
            System.out.println("Caught exception in getLastMonthRentSheetPerUser() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RentSheet> getRentHistoryForUser(long userId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(RentSheet.class);
            criteria.add(Restrictions.eq("user.id", userId));
            criteria.addOrder(Order.desc("rentGeneratedDate"));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in getRentHistoryForUser() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

}
