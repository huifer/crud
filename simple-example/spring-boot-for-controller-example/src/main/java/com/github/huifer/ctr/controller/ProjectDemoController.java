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

package com.github.huifer.ctr.controller;

import com.alibaba.fastjson.JSON;
import com.github.huifer.crud.ctr.entity.ResultVO;
import com.github.huifer.ctr.entity.ProjectDemo;
import com.github.huifer.ctr.mapper.ProjectDemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class ProjectDemoController {

  @Autowired
  private ProjectDemoMapper projectDemoMapper;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @PostMapping("/add")
  public ResultVO add(
      @RequestBody ProjectDemo req
  ) {
    int i = projectDemoMapper.insertSelective(req);
    if (i > 0) {
      stringRedisTemplate.opsForHash()
          .put("demo", String.valueOf(req.getId()), JSON.toJSONString(req));
      return ResultVO.success();
    }
    else {
      return ResultVO.failed();
    }
  }
}
