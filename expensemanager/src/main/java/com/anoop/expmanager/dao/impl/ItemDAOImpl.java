package com.anoop.expmanager.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.DO.UserIncommExpenseSummaryDO;
import com.anoop.expmanager.dao.ItemDAO;
import com.anoop.expmanager.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAOImpl implements ItemDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Item> findAllItemBetweenDate(Date startDate, Date endDate) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.ge("purchasedDate", startDate));
            criteria.add(Restrictions.le("purchasedDate", endDate));
            criteria.createAlias("user", "u");
            criteria.addOrder(Order.asc("u.displayName"));
            criteria.addOrder(Order.desc("purchasedDate"));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in findAll() : " + e);
        } finally {
            session.close();
        }
        return null;
	}

	@Override
	public List<Item> findAllItemPerUserMonthAndYear(long userID, int month, int year) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Item.class);
//			criteria.add(Restrictions.eq("month", month));
//			criteria.add(Restrictions.eq("year", year));
//			criteria.add(Restrictions.eq("userID", userID));
			return criteria.list();
		} catch (Exception e) {
			System.out.println("Caught exception in findAllItemPerUserMonthAndYear() : " + e);
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void saveItem(Item item) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.saveOrUpdate(item);
		} catch (Exception e) {
			System.out.println("Caught exception in saveItem() : " + e);
		} finally {
			session.close();
		}

	}

	@Override
	public List<Item> findAll() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Item.class);
            criteria.addOrder(Order.desc("id"));
			return criteria.list();
		} catch (Exception e) {
			System.out.println("Caught exception in findAll() : " + e);
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public List<UserIncommExpenseSummaryDO> calculateUserExpense(int month, int year) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.eq("month", month));
			criteria.add(Restrictions.eq("year", year));
			return criteria.list();
		} catch (Exception e) {
			System.out.println("Caught exception in findAll() : " + e);
		} finally {
			session.close();
		}
		return null;
	}

    @Override
    public List<Item> findAllItemPerUserAndDate(long userID, Date startDate, Date endDate) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.eq("user.id", userID));
//            criteria.add(Restrictions.between("purchasedDate",startDate,endDate));
            criteria.add(Restrictions.ge("purchasedDate", startDate));
            criteria.add(Restrictions.le("purchasedDate", endDate));
            criteria.addOrder(Order.desc("purchasedDate"));
            return criteria.list();
        } catch (Exception e) {
            System.out.println("Caught exception in findAll() : " + e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public double calculateUserExpenseBetweenDate(long userID, Date startDate, Date endDate) {
        System.out.println("userID : " + userID);
        System.out.println("startDate" + startDate);
        System.out.println("endDate" + endDate);
        Session session = null;
        Object obj = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.eq("user.id", userID));
            criteria.add(Restrictions.ge("purchasedDate", startDate));
            criteria.add(Restrictions.le("purchasedDate", endDate));
            criteria.addOrder(Order.desc("purchasedDate"));

            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.sum("price"));
            criteria.setProjection(projectionList);

            System.out.println("criteria.uniqueResult() : " +criteria.uniqueResult());
            obj = criteria.uniqueResult();
            if(obj == null) {
                return 0.0;
            }
            return (Double) obj;
        } catch (Exception e) {
            System.out.println("Caught exception in calculateUserExpenseBetweenDate() : " + e);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return 0;
    }

}
