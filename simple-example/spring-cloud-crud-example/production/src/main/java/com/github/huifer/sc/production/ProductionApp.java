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

package com.github.huifer.sc.production;

import com.github.huifer.crud.common.beans.EnableCrudTemplate;
import com.github.huifer.crud.common.daotype.DaoType;
import com.github.huifer.crud.ctr.beans.EnableCrudController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableCrudController(scanPackages = {"com.github.huifer.sc.production"})
@EnableCrudTemplate(daoType = DaoType.MYBATIS)
@EnableFeignClients
public class ProductionApp {

  public static void main(String[] args) {
    SpringApplication.run(ProductionApp.class, args);
  }
}
