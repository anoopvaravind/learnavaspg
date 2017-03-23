package com.anoop.expmanager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.AccountDAO;
import com.anoop.expmanager.model.Account;
import com.anoop.expmanager.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Account getCurrentAccountDetails() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.eq("month", Util.getCurrentMonth()));
			criteria.add(Restrictions.eq("year", Util.getCurrentYear()));
			return (Account) criteria.uniqueResult();
		} catch (Exception e) {
			System.out.println("Caught exception in getAccountHistory() : " + e);
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Account> getAccountHistory() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Account.class);
			return criteria.list();
		} catch (Exception e) {
			System.out.println("Caught exception in getAccountHistory() : " + e);
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void createAccount(Account account) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.saveOrUpdate(account);
		} catch (Exception e) {
			System.out.println("Caught exception in createAccount() : " + e);
            e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public List<Account> getAccountDetailsPerMonthAndYear(int month, int year) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.eq("month", month));
			criteria.add(Restrictions.eq("year", year));
			return criteria.list();
		} catch (Exception e) {
			System.out.println("Caught exception in getAccountHistory() : " + e);
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Account getLatestAccount() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Account.class);
			criteria.addOrder(Order.desc("ceatedDate"));
			return (Account) criteria.uniqueResult();
		} catch (Exception e) {
			System.out.println("Caught exception in getAccountHistory() : " + e);
		} finally {
			session.close();
		}
		return null;
	}

}
