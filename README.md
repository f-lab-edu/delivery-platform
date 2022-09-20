# Delivery-Platform

# Project Setting

## APM

### apm-server

```bash
$ docker compose -f apm-docker-compose.yml up
```

### apm-agent options

- 아래 옵션을 VM Options 에 추가합니다.

```
-javaagent:./agent/elastic-apm-agent-1.33.0.jar
-Delastic.apm.disable_send=false
-Delastic.apm.environment=local
-Delastic.apm.service_name=deliveryplatform
-Delastic.apm.enable_log_correlation=true
-Delastic.apm.application_packages=org.flab.deliveryplatform
-Delastic.apm.trace_methods_duration_threshold=1ms
-Delastic.apm.transaction_sample_rate=1
-Delastic.apm.server_urls=http://localhost:8200
-Delastic.apm.secret_token=
-Delastic.apm.span_frames_min_duration=1ms
-Delastic.apm.span_min_duration=0ms
-Delastic.apm.trace_methods=org.flab.deliveryplatform.*
-Delastic.apm.max_queue_size=2048
```

## Spring Boot Admin

- spring-boot-admin-server module 의 SpringBootAdminServerApplication 를 실행 후
  `http://localhost:18090` 로 접속하여 애플리케이션 관련 정보들을 모니터링 할 수 있습니다.

## Style

### pre-commit

```bash
$ ./gradlew installGitHook
```
