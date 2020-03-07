package com.fly.system.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.common.constant.ServiceNameConstants;
import com.fly.common.core.domain.Result;
import com.fly.system.domain.SysUser;
import com.fly.system.feign.fallback.RemoteSysUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = ServiceNameConstants.SYSTEM_PROVIDER, contextId = "USER", fallbackFactory = RemoteSysUserServiceFallback.class)
public interface RemoteSysUserService {

    @GetMapping("/users/pageNum/{pageNum}/pageSize/{pageSize}")
    Result<Page<SysUser>> page(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody SysUser sysUser);

    @GetMapping("/users/{id}")
    Result<SysUser> getById(@PathVariable("id") Long id);

    @PostMapping("/users")
    Result add(@RequestBody SysUser sysUser);

    @PutMapping("/users/{id}")
    Result update(@PathVariable("id") Long id, @RequestBody SysUser sysUser);

    @DeleteMapping("/users/{ids}")
    Result delete(@PathVariable Long[] ids);

}
