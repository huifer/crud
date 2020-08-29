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

package com.github.huifer.ctr.conf;

import com.github.huifer.crud.ctr.servlet.OcaServlet;
import com.github.huifer.ctr.filter.MyFilter;
import com.github.huifer.ctr.filter.MyServletListener;
import java.util.Arrays;
import javax.servlet.ServletContextListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForControllerConfig {

  @Bean
  FilterRegistrationBean myFilterRegistration() {
    FilterRegistrationBean frb = new FilterRegistrationBean();
    frb.setFilter(new MyFilter());
    frb.setUrlPatterns(Arrays.asList("/*"));
    return frb;
  }


  @Bean
  ServletRegistrationBean myServletRegistration() {
    ServletRegistrationBean srb = new ServletRegistrationBean();
    srb.setServlet(new OcaServlet());
    srb.setUrlMappings(Arrays.asList("/rest/*"));
    return srb;
  }


  @Bean
  ServletListenerRegistrationBean<ServletContextListener> myServletListener() {
    ServletListenerRegistrationBean<ServletContextListener> srb =
        new ServletListenerRegistrationBean<>();
    srb.setListener(new MyServletListener());
    return srb;
  }
}
