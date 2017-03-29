package com.anoop.expmanager.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.anoop.expmanager.dao.SettingsDAO;
import com.anoop.expmanager.model.RentSheet;
import com.anoop.expmanager.model.Settings;
import com.anoop.expmanager.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class SettingsDAOImpl implements SettingsDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public HashMap<String, String> loadSetstingsMap() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Settings.class);
            criteria.add(Restrictions.eqOrIsNull("enable", true));
            List<Settings> settingslist = criteria.list();
            if (settingslist == null) {
                System.out.println("No settings found in DB !!!!!!!!!!");
                return null;
            }
            HashMap<String, String> settingsMap = new HashMap<String, String>();
            for (Settings setting : settingslist) {
                settingsMap.put(setting.getKey(), setting.getValue());
            }
            return settingsMap;
        } catch (Exception e) {
            System.out.println("Caught exception in getCurrentMonthPaidDetails() : " + e);
        } finally {
            session.close();
        }
        return null;
    }
}
