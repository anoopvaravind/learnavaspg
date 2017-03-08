package com.anoop.expmanager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	public List<Item> findAllItemPerMonthAndYear(int month, int year) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Item.class);
			criteria.add(Restrictions.eq("month", month));
			criteria.add(Restrictions.eq("year", year));
			return criteria.list();
		} catch (Exception e) {
			System.out.println("Caught exception in findAllItemPerMonthAndYear() : " + e);
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
			criteria.add(Restrictions.eq("month", month));
			criteria.add(Restrictions.eq("year", year));
			criteria.add(Restrictions.eq("userID", userID));
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

}
