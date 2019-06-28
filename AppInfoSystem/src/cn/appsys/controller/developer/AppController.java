package cn.appsys.controller.developer;

import cn.appsys.controller.BaseController;
import cn.appsys.pojo.AppInfo;
import cn.appsys.service.devUser.AppInfoService;
import cn.appsys.service.devUser.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ：suPengCheng
 * @date ：Created in 19-6-27 15:01
 */
@RequestMapping("/app")
@Controller
public class AppController extends BaseController {

    @Autowired
    private AppInfoService service;


    @RequestMapping("/getAppListPage")
    public String getAppListPage(AppInfo appInfo){
        List<AppInfo> appInfoList = service.getAppListPage(appInfo);
        request.setAttribute("appInfoList",appInfoList);
        System.out.println(appInfo);
        return "";
    }


}
