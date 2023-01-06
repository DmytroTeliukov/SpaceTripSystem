package org.dteliukov.dao;

import org.dteliukov.dao.mysql.MySqlDaoFactory;

public class DaoRepository {
    private static DaoFactory dao = null;

    public static DaoFactory getDao(TypeDao typeDao) {
        if (dao == null) {
            if (typeDao == TypeDao.MySQL) {
                dao = new MySqlDaoFactory();
            }
        }
        return dao;
    }
}
