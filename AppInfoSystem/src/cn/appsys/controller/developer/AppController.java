package cn.appsys.controller.developer;

import cn.appsys.controller.BaseController;
import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.devUser.AppInfoService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.EmptyUtil;
import cn.appsys.tools.PageBean;
import cn.appsys.tools.ReturnResult;
import cn.appsys.tools.params.AppInfoParams;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：suPengCheng
 * @date ：Created in 19-6-27 15:01
 */
@RequestMapping("/app")
@Controller
public class AppController extends BaseController {

    @Autowired
    private AppInfoService appInfoService;

    @RequestMapping("/getAppListPage")
    public String getAppListPage(AppInfoParams appInfoParams) {
        // 查询信息
        String softwareName = appInfoParams.getSoftwareName();
        Integer status = appInfoParams.getStatus();
        Integer flatformId = appInfoParams.getFlatformId();
        Integer categoryLevel1 = appInfoParams.getCategoryLevel1();
        Integer categoryLevel2 = appInfoParams.getCategoryLevel2();
        Integer categoryLevel3 = appInfoParams.getCategoryLevel3();

        System.out.println(softwareName);
        if (status == null || status == -1) {
            appInfoParams.setStatus(null);
        }
        System.out.println(status);
        if (flatformId == null || flatformId == -1) {
            appInfoParams.setFlatformId(null);
        }
        System.out.println(flatformId);
        if (categoryLevel1 == null || categoryLevel1 == -1) {
            appInfoParams.setCategoryLevel1(null);
        }
        System.out.println(categoryLevel1);
        if (categoryLevel2 == null || categoryLevel2 == -1) {
            appInfoParams.setCategoryLevel2(null);
        }
        System.out.println(categoryLevel2);
        if (categoryLevel3 == null || categoryLevel3 == -1) {
            appInfoParams.setCategoryLevel3(null);
        }
        System.out.println(categoryLevel3);
        // 分页信息
        PageBean pageBean = getPageBean(appInfoParams);
        request.setAttribute("pageBean", pageBean);
        // 表格展示信息
        List<AppInfo> appInfoList = appInfoService.getAppListPage(appInfoParams);
        // 列表名称
        appInfoList = appInfoService.getCategoryName(appInfoList);
        request.setAttribute("appInfoList", appInfoList);
        // app的所有状态
        List<DataDictionary> appStatusList = getDataDictionary("APP_STATUS");
        request.setAttribute("appStatusList", appStatusList);
        // 所属平台
        List<DataDictionary> publishStatusList = getDataDictionary("PUBLISH_STATUS");
        request.setAttribute("publishStatusList", publishStatusList);
        // 一级分类
        List<AppCategory> categoryLevel1List = appInfoService.getAppCategoryListByParentId(0);
        request.setAttribute("categoryLevel1List", categoryLevel1List);
        // 二级分类
        List<AppCategory> categoryLevel2List = new ArrayList<>();
        for (AppCategory category : categoryLevel1List) {
            categoryLevel2List.addAll(this.getCategoryByParentId(category.getId()));
        }
        request.setAttribute("categoryLevel2List", categoryLevel2List);
        // 三级分类
        List<AppCategory> categoryLevel3List = new ArrayList<>();
        for (AppCategory category : categoryLevel2List) {
            categoryLevel3List.addAll(this.getCategoryByParentId(category.getId()));
        }
        request.setAttribute("categoryLevel3List", categoryLevel3List);
        System.out.println(appInfoList);
        request.setAttribute("querySoftwareName", softwareName);
        request.setAttribute("queryStatus", status);
        request.setAttribute("queryFlatFormId", flatformId);
        request.setAttribute("queryCategoryLevel1", categoryLevel1);
        request.setAttribute("queryCategoryLevel2", categoryLevel2);
        request.setAttribute("queryCategoryLevel3", categoryLevel3);
        return "developer/appInfoList";
    }

    // 根据父id获取Category
    @RequestMapping("/getCategoryByParentId")
    @ResponseBody
    public List<AppCategory> getCategoryByParentId(Integer pid) {
        return appInfoService.getAppCategoryListByParentId(pid);
    }

    public List<DataDictionary> getDataDictionary(String tCode) {
        return appInfoService.getAllTypeNameByTypeCode(tCode);
    }

    /**
     * getAppListPage方法的分页信息
     */
    private PageBean getPageBean(AppInfoParams appInfoParams) {
        Integer currentPage = appInfoParams.getCurrentPage();
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
            appInfoParams.setCurrentPage(currentPage);
        }
        Integer pageSize = appInfoParams.getPageSize();
        if (pageSize == null || pageSize < 1) {
            pageSize = 5;
            appInfoParams.setPageSize(pageSize);
        }
        int totalCount = appInfoService.getAppListPageCount(appInfoParams);
        PageBean pageBean = new PageBean(currentPage, pageSize, totalCount);
        pageBean.setUrl("app/getAppListPage");
        return pageBean;
    }

    /**
     * 跳转到添加app信息页面
     *
     * @return
     */
    @RequestMapping(value = "/toAddAppInfo", method = RequestMethod.GET)
    public String toAddAppInfo() {
        return "developer/appInfoAdd";
    }

    @RequestMapping(value = "/apkNameExist", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult apkNameExist(String APKName) {
        ReturnResult result = new ReturnResult();
        if (EmptyUtil.isEmpty(APKName)) {
            result.setMessage("empty");
        } else {
            AppInfo appInfo = new AppInfo(APKName);
            AppInfo newAppInfo = appInfoService.getOneAppInfo(appInfo);
            if (newAppInfo == null) {
                result.setMessage("noExist");
            } else {
                result.setMessage("exist");
            }
        }
        System.out.println(result);
        return result;
    }

    @RequestMapping("/getFlatForm")
    @ResponseBody
    public List<DataDictionary> getFlatForm(String tCode) {
        return this.getDataDictionary(tCode);
    }

    @RequestMapping(value = "/appInfoAddSave", method = RequestMethod.POST)
    public String appInfoAddSave(AppInfo appInfo, @RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach) {
        String logoPicPath = ""; // url的来源路径
        String logoLocPath = ""; // LOGO图片的服务器存储路径
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + java.io.File.separator + "uploadFiles");
            String oldFileName = attach.getOriginalFilename();// 源文件名
            String preFix = FilenameUtils.getExtension(oldFileName);// 源文件后缀名称
            int fileSize = 500000;
            if (attach.getSize() > fileSize) {
                request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                return "developer/appInfoAdd";
            } else if (preFix.equalsIgnoreCase("jpg") || preFix.equalsIgnoreCase("png")
                    || preFix.equalsIgnoreCase("jepg") ||
                    preFix.equalsIgnoreCase("pneg")) { // 忽略大小写，上传图片
                String fileName = appInfo.getAPKName() + ".jpg"; // 上传LOGO图片名称
                File targetFile = new File(path, fileName);
                if (targetFile != null) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
                    return "developer/appInfoAdd";
                }
                logoPicPath = request.getContextPath() + "/statics/uploadFiles/" + fileName;
                logoLocPath = path + File.separator + fileName;
            } else {
                request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
                return "developer/appInfoAdd";
            }
        }
        appInfo.setCreatedBy(((DevUser) session.getAttribute(Constants.USER_SESSION)).getId());
        appInfo.setCreationDate(new Date());
        appInfo.setLogoPicPath(logoPicPath);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setDevId(((DevUser) session.getAttribute(Constants.USER_SESSION)).getId());
        appInfo.setStatus(1);
        try {
            if (appInfoService.appInfoAddSave(appInfo)) {
                return "redirect:/app/getAppListPage";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "app/appInfoList";
    }


}
