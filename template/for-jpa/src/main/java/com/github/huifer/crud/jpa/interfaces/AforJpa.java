package com.github.huifer.crud.jpa.interfaces;

import com.github.huifer.crud.common.intefaces.A;
import org.springframework.data.repository.CrudRepository;

public interface AforJpa<Id, T> extends CrudRepository<T, Id>, A<Id, T> {


}
