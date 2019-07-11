package cn.appsys.service.devUser;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.tools.params.AppInfoParams;

import java.util.List;

public interface AppInfoService {

    /**
     * 分页条件查询app
     */
    List<AppInfo> getAppListPage(AppInfoParams appInfoParams);

    /**
     * 获取列表名称
     */
    List<AppInfo> getCategoryName(List<AppInfo> appInfoList);

    /**
     * getAppListPage对应的总记录数
     */
    int getAppListPageCount(AppInfoParams appInfoParams);

    /**
     * 获取所有app状态
     */
    List<DataDictionary> getAllTypeNameByTypeCode(String typeCode);

    /**
     * 根据父id获取分类列表
     */
    List<AppCategory> getAppCategoryListByParentId(int parentId);

    /**
     * 获取单个app信息
     */
    AppInfo getOneAppInfo(AppInfo appInfo);

    /**
     * 添加app信息
     */
    boolean appInfoAddSave(AppInfo appInfo);
}
