package cn.appsys.service.devUser;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.tools.params.AppInfoParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：suPengCheng
 * @date ：Created in 19-6-28 11:21
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    private AppInfoMapper mapper;

    @Override
    public List<AppInfo> getAppListPage(AppInfoParams appInfoParams) {
        appInfoParams.setCurrentPage((appInfoParams.getCurrentPage() - 1) * appInfoParams.getPageSize());
        return mapper.getAppListPage(appInfoParams);
    }

    @Override
    public List<AppInfo> getCategoryName(List<AppInfo> appInfoList) {
        for (AppInfo appInfo : appInfoList) {
            Integer categoryLevel1 = appInfo.getCategoryLevel1();
            Integer categoryLevel2 = appInfo.getCategoryLevel2();
            Integer categoryLevel3 = appInfo.getCategoryLevel3();
            appInfo.setCategoryLevel1Name(mapper.getCategoryNameById(categoryLevel1));
            appInfo.setCategoryLevel2Name(mapper.getCategoryNameById(categoryLevel2));
            appInfo.setCategoryLevel3Name(mapper.getCategoryNameById(categoryLevel3));
        }
        return appInfoList;
    }


    @Override
    public int getAppListPageCount(AppInfoParams appInfoParams) {
        return mapper.getAppListPageCount(appInfoParams);
    }

    @Override
    public List<DataDictionary> getAllTypeNameByTypeCode(String typeCode) {
        return mapper.getAllTypeNameByTypeCode(typeCode);
    }

    @Override
    public List<AppCategory> getAppCategoryListByParentId(int parentId) {
        return mapper.getAppCategoryListByParentId(parentId);
    }

    @Override
    public AppInfo getOneAppInfo(AppInfo appInfo) {
        return mapper.getOneAppInfo(appInfo);
    }

    @Override
    public boolean appInfoAddSave(AppInfo appInfo) {
        return mapper.appInfoAddSave(appInfo) > 0;
    }
}
