package cn.appsys.tools.params;

import cn.appsys.pojo.AppInfo;
import lombok.Data;

/**
 * @author ：suPengCheng
 * @date ：Created in 19-6-29 8:38
 */
@Data
public class AppInfoParams extends AppInfo {
    // 当前页
    private Integer currentPage;
    // 分页标准
    private Integer pageSize;
    // 父id
    private Integer parentId;
}
