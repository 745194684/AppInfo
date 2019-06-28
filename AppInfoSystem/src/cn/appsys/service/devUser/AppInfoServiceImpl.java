package cn.appsys.service.devUser;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.pojo.AppInfo;
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
    public List<AppInfo> getAppListPage(AppInfo appInfo) {
        return mapper.getAppListPage(appInfo);
    }
}
