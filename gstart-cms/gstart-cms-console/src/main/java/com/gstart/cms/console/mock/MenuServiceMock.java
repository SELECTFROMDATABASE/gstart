package com.gstart.cms.console.mock;

import com.gstart.cms.rpc.api.MenuService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 15:49 2019/3/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@FeignClient("cms-service")
public interface MenuServiceMock extends MenuService {
}
