# Gateway

> Spring Cloud Gateway

- Cloud Version: 2020.0.4
- Spring Boot Version: 2.5.7
- Spring Framework Version: 5.3.13

测试准备：
- pip install httpbin
- python -m httpbin.core --port 9000
- 如果需要使用gateway自带的request-rate-limiter,需要本地启动redis

## Changes
由于`Spring Cloud 2020.0.0`发布后 

以下模块已从`spring-cloud-netflix`中删除
- `spring-cloud-netflix-archaius`
- `spring-cloud-netflix-concurrency-limits`
- `spring-cloud-netflix-core`
- `spring-cloud-netflix-dependencies`
- `spring-cloud-netflix-hystrix`
- `spring-cloud-netflix-hystrix-contract`
- `spring-cloud-netflix-hystrix-dashboard`
- `spring-cloud-netflix-hystrix-stream`
- `spring-cloud-netflix-ribbon`
- `spring-cloud-netflix-sidecar`
- `spring-cloud-netflix-turbine`
- `spring-cloud-netflix-turbine-stream`
- `spring-cloud-netflix-zuul`
- `spring-cloud-starter-netflix-archaius`
- `spring-cloud-starter-netflix-hystrix`
- `spring-cloud-starter-netflix-hystrix-dashboard`
- `spring-cloud-starter-netflix-ribbon`
- `spring-cloud-starter-netflix-turbine`
- `spring-cloud-starter-netflix-turbine-stream`
- `spring-cloud-starter-netflix-zuul`
- `在发布系列项目中删除了对ribbon、hystrix 和zuul 的支持`




> 关于替代方案，官网给出的建议： https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now

**Replacements**

We recommend the following as replacements for the functionality provided by these modules.

|    Current    | Replacement |
| ---------- | --- |
| Hystrix |  [Resilience4j](https://github.com/resilience4j/resilience4j) |
| Hystrix Dashboard / Turbine       |  Micrometer + Monitoring System |
|Ribbon|Spring Cloud Loadbalancer|
|Zuul 1|Spring Cloud Gateway|
|Achaius 1|Spring Boot external config + Spring Cloud Config|

`OpenFeign`中的熔断器`Hystrix`在`2020.0.0`后不能继续使用

```yaml
feign:
    hystrix:
        enable: true
```
取而代之的是：
```yaml
feign:
    circuitbreaker:
        enable: true
```
服务降价需要替代方案，如： `Resilience4j`

官网说明： https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2020.0-Release-Notes#known-issues

博客参考： [Spring Cloud 2020.0.0正式发布，再见了Netflix](https://www.cnblogs.com/yourbatman/p/14182433.html)

网友整理替代方案

![](doc/img/Spring Cloud Netfix Replacements.png)