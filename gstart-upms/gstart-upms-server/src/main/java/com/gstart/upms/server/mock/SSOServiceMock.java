package com.gstart.upms.server.mock;

import com.gstart.upms.rpc.api.SSOService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("upms-service")
public interface SSOServiceMock extends SSOService {
}
