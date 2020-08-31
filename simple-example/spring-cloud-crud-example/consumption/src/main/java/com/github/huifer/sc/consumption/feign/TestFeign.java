package com.github.huifer.sc.consumption.feign;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>Title: TestFeign.java</p>
 *
 * @author Carlton
 * @date 2019年4月29日 上午9:26:36
 */
@FeignClient("asf")
public interface TestFeign {

  @RequestLine("POST /rest/project/demo/add")
  Object getUser(@RequestBody Object datas);

}
