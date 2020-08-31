package com.github.huifer.sc.consumption.ctr;

import com.github.huifer.sc.consumption.beans.FeignBean;
import com.github.huifer.sc.consumption.feign.TestFeign;
import feign.Feign;
import feign.Feign.Builder;
import feign.Request.Options;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 【1】import FeignClientsConfiguration.class
@Import(FeignClientsConfiguration.class)
public class TestController {

  // 不需要 @Autowired
  TestFeign testFeign;

  // 【2】 构造函数添加 @Autowired ，注入encoder，decoder，构建 TestFeign
  @Autowired
  public TestController(FeignBean feignBean, Encoder encoder, Decoder decoder) {
    // options方法指定连接超时时长及响应超时时长，retryer方法指定重试策略
    Builder builder = Feign.builder();
    // 设置http basic验证
    builder = builder.contract(new feign.Contract.Default()).requestInterceptor(
        new BasicAuthRequestInterceptor(feignBean.getAdminName(), feignBean.getAdminPassword()));
    // 【3】设置编码，不然会报错feign.codec.EncodeException
    builder = builder.encoder(encoder).decoder(decoder);
    // options方法指定连接超时时长及响应超时时长，retryer方法指定重试策略
    builder = builder.options(new Options(feignBean.getOpion_conn(), feignBean.getOpion_read()))
        .retryer(new Retryer.Default(feignBean.getRetry_period(), feignBean.getRetry_maxPeriod(),
            feignBean.getRetry_maxAttempts()));
    // 【4】 target 链接目标feing，并指定访问域名
    testFeign = builder.target(TestFeign.class, feignBean.getUrl());
//		testFeign = builder.target(Target.EmptyTarget.create(TestFeign.class));
    System.out.println(000);
  }

  @GetMapping("/asd")
  public Object asd() {
    Map<String, String> map = new HashMap<>();
    map.put("name", "zhangsan");
    Object user = testFeign.getUser(map);
    return user;
  }
}
