package com.github.huifer.crud.daotype;

public class DaoTypeThreadLocal {
    private static final ThreadLocal<DaoType> daoTypeThreadLocal = new ThreadLocal<>();


    private DaoTypeThreadLocal() throws IllegalAccessException {
        throw new IllegalAccessException("this is a utils !");
    }

    /**
     * get dao type
     *
     * @return daoTYpe
     */
    public static DaoType getDaoType() {
        return daoTypeThreadLocal.get();
    }

    /**
     * setting dao type
     *
     * @param daoType dao type
     */
    public static void setDaoType(DaoType daoType) {
        DaoType cache = daoTypeThreadLocal.get();
        if (cache == null) {
            daoTypeThreadLocal.set(daoType);
        }
    }

}
