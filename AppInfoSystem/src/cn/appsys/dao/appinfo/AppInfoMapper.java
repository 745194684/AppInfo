package cn.appsys.dao.appinfo;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.tools.params.AppInfoParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AppInfoMapper {
    /**
     * 分页条件查询app
     */
    List<AppInfo> getAppListPage(AppInfoParams appInfoParams);

    /**
     * 获取列表名称
     */
    String getCategoryNameById(Integer id);

    /**
     * getAppListPage对应的总记录数
     */
    int getAppListPageCount(AppInfoParams appInfoParams);

    /**
     * 获取所有app状态
     */
    List<DataDictionary> getAllTypeNameByTypeCode(@Param("typeCode") String typeCode);

    /**
     * 根据父id获取分类列表
     */
    List<AppCategory> getAppCategoryListByParentId(@Param("parentId") int parentId);

    /**
     * 获取单个app信息
     */
    AppInfo getOneAppInfo(AppInfo appInfo);

    /**
     * 添加app信息
     */
    Integer appInfoAddSave(AppInfo appInfo);
}
