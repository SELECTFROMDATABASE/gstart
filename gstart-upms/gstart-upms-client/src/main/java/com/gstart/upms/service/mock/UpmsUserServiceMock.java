package com.gstart.upms.service.mock;

import com.gstart.upms.rpc.api.UpmsUserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("upms-service")
public interface UpmsUserServiceMock extends UpmsUserService {
}
