package com.gstart.upms.service.mock;

import com.gstart.upms.rpc.api.UpmsMenuService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 21:27
 */
@FeignClient("upms-service")
public interface UpmsMenuServiceMock extends UpmsMenuService {
}
