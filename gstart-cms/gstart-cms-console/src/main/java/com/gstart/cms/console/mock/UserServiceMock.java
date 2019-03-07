package com.gstart.cms.console.mock;

import com.gstart.cms.rpc.api.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cms-service")
public interface UserServiceMock extends UserService {
}
