package cn.appsys.service.devUser;

import cn.appsys.pojo.AppInfo;

import java.util.List;

public interface AppInfoService {

    /**
     * 分页条件查询app
     */
    List<AppInfo> getAppListPage(AppInfo appInfo);
}
