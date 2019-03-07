package com.gstart.upms.service.mock;

import com.gstart.upms.rpc.api.UpmsRoleService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 21:26
 */
@FeignClient("upms-service")
public interface UpmsRoleServiceMock extends UpmsRoleService {
}
