package com.gstart.upms.service.mock;

import com.gstart.upms.rpc.api.UpmsApiService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("upms-service")
public interface UpmsApiServiceMock extends UpmsApiService {
}
