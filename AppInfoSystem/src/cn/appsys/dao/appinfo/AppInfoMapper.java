package cn.appsys.dao.appinfo;

import cn.appsys.pojo.AppInfo;

import java.util.List;

public interface AppInfoMapper {

    List<AppInfo> getAppListPage(AppInfo appInfo);

}
