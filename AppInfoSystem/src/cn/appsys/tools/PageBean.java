package cn.appsys.tools;

import lombok.Data;

/**
 * @author ：suPengCheng
 * @date ：Created in 19-6-21 11:53
 */
@Data
public class PageBean {
    // 当前页
    private Integer currentPage;
    // 分页标准
    private Integer pageSize;
    // 总记录数
    private Integer totalCount;
    // 总页数
    private Integer pageCount;
    // 跳转位置
    private String url;
    // 数据
    private Object object;


    public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        pageCount = (totalCount + pageSize - 1) / pageSize;
    }


}
