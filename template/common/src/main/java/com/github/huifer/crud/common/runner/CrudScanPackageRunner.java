/*
 *
 * Copyright 2020-2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.huifer.crud.common.runner;


import com.github.huifer.crud.common.annotation.CacheKey;
import com.github.huifer.crud.common.annotation.entity.CacheKeyEntity;
import com.github.huifer.crud.common.utils.EnableAttrManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CrudScanPackageRunner implements CommandLineRunner, Ordered {


  /**
   * key: entity class  value:  {@link CacheKey }
   */
  public static Map<Class<?>, CacheKeyEntity> PACKAGE_CACHE_INFO = new HashMap<>();

  public static String key(Class<?> clazz) {
    return PACKAGE_CACHE_INFO.get(clazz).getKey();
  }

  public static CacheKeyEntity cacheKeyEntity(Class<?> clazz) {
    return PACKAGE_CACHE_INFO.get(clazz);
  }

  @Override
  public void run(String... args) throws Exception {
    scanPackages();
  }

  private void scanPackages() throws IOException {
    String[] scanPackageDao = EnableAttrManager.getScanPackageDao();
    if (scanPackageDao != null) {

      String[] packages = scanPackageDao.clone();
      for (String pack : packages) {
        Set<Class<?>> classes = ScanUtils.getClasses(pack);
        for (Class<?> aClass : classes) {
          if (!aClass.isAnnotation() && !aClass.isEnum() && !aClass.isInterface()) {
            keyEntity(aClass);
          }
        }
      }
    }
  }


  /**
   * put data
   *
   * @param cacheKeyEntity
   */
  private void put(CacheKeyEntity cacheKeyEntity) {
    if (cacheKeyEntity != null) {
      Class<?> type = cacheKeyEntity.getType();
      CacheKeyEntity ck = PACKAGE_CACHE_INFO.get(type);
      if (ck != null) {
        throw new RuntimeException("has a cache" + type);
      }
      else {
        PACKAGE_CACHE_INFO.put(cacheKeyEntity.getType(), cacheKeyEntity);
      }
    }
  }

  /**
   * from clazz get {@code CacheKey} convert to {@code CacheKeyEntity}
   *
   * @param clazz class
   * @return CacheKey attr
   */
  private void keyEntity(Class<?> clazz) {
    CacheKey annotation = clazz.getAnnotation(CacheKey.class);
    if (annotation != null) {
      CacheKeyEntity cacheKeyEntity = new CacheKeyEntity();
      cacheKeyEntity.setKey(annotation.key());
      cacheKeyEntity.setType(annotation.type());
      cacheKeyEntity.setIdFiled(annotation.idFiled());
      cacheKeyEntity.setIdMethod(annotation.idMethod());
      put(cacheKeyEntity);
    }
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}