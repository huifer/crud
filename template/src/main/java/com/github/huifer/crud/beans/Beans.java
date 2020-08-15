package com.github.huifer.crud.beans;

import com.github.huifer.crud.daotype.DaoType;
import com.github.huifer.crud.daotype.DaoTypeThreadLocal;
import com.github.huifer.crud.interfaces.CrudTemplate;
import com.github.huifer.crud.service.CrudHashTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.github.huifer.crud.*"})
public class Beans {


    @Bean
    public CrudTemplate crudTemplate() {
        DaoType daoType = DaoTypeThreadLocal.getDaoType();


        return new CrudHashTemplate();
    }


}