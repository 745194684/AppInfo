<%@ page contentType="text/html;charset=UTF-8" %>
<html id="html">
<head>
    <title>Title</title>
    <%@include file="common/header.jsp" %>
    <link type="text/css" href="${prc}/statics/localcss/appinfolist.css">
    <script src="${prc}/statics/js/jquery.min.js"></script>
    <script src="${prc}/statics/localjs/applist.js"></script>
</head>
<body>
<div class="row">
    <div class="x_panel">
        <div class="x_title">
            <h2>APP 信息维护<i class="fa fa-user"></i>
                <small>${userSession.devName} - 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^</small>
            </h2>
            <ul class="nav navbar-right panel_toolbox"></ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_panel">
            <div class="x_content">
                <br/>
                <form method="get" action="${prc}/app/getAppListPage" class="form-horizontal form-label-left">
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input id="softwareName" name="softwareName" type="text"
                                           class="form-control col-md-7 col-xs-12" value="${querySoftwareName}">
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select id="status" class="form-control" name="status">
                                        <option value="-1">--请选择--</option>
                                        <c:forEach items="${appStatusList}" var="appStatus">
                                            <option
                                                    <c:if test="${queryStatus == appStatus.valueId}">selected="selected"</c:if>
                                                    value="${appStatus.valueId}">${appStatus.valueName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select id="flatformId" class="form-control" name="flatformId">
                                        <option value="-1">--请选择--</option>
                                        <c:forEach items="${publishStatusList}" var="publishStatus">
                                            <option <c:if test="${queryFlatFormId == publishStatus.valueId}">selected="selected"</c:if> value="${publishStatus.valueId}">${publishStatus.valueName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control" id="queryCategoryLevel1" name="categoryLevel1">
                                        <option value="-1">--请选择--</option>
                                        <c:forEach items="${categoryLevel1List}" var="categoryLevel1">
                                            <option <c:if test="${queryCategoryLevel1 == categoryLevel1.id}">selected="selected"</c:if> value="${categoryLevel1.id}">${categoryLevel1.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control" id="queryCategoryLevel2" name="categoryLevel2">
                                        <option value="-1">--请选择--</option>
                                        <c:forEach items="${categoryLevel2List}" var="categoryLevel2">
                                            <option <c:if test="${queryCategoryLevel2 == categoryLevel2.id}">selected="selected"</c:if> value="${categoryLevel2.id}">${categoryLevel2.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control" id="queryCategoryLevel3" name="categoryLevel3">
                                        <option value="-1">--请选择--</option>
                                        <c:if test="${categoryLevel3List != null}">
                                            <c:forEach items="${categoryLevel3List}" var="categoryLevel3">
                                                <option <c:if test="${queryCategoryLevel3 == categoryLevel3.id}">selected="selected"</c:if> value="${categoryLevel3.id}">${categoryLevel3.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <br/>
                        <li>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <input type="submit" class="btn btn-success" value="查询"/>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
        <div class="x_content">
            <a href="${pageContext.request.contextPath}/app/toAddAppInfo" class="btn btn-success btn-sm">新增APP基础信息</a>
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>软件名称</th>
                    <th>软件大小</th>
                    <th>所属平台</th>
                    <th>所属分类</th>
                    <th>下载次数</th>
                    <th>版本号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appInfoList}" var="appInfo">
                    <tr>
                        <td>${appInfo.softwareName}</td>
                        <td>${appInfo.softwareSize}</td>
                        <td>
                            <c:if test="${appInfo.flatformId == 1}">
                                手机
                            </c:if>
                            <c:if test="${appInfo.flatformId == 2}">
                                平板
                            </c:if>
                            <c:if test="${appInfo.flatformId == 3}">
                                通用
                            </c:if>
                        </td>
                        <td>${appInfo.categoryLevel1Name}-->
                                ${appInfo.categoryLevel2Name}-->
                                ${appInfo.categoryLevel3Name}
                        </td>
                        <td>${appInfo.downloads}</td>
                        <td>${appInfo.versionNo}</td>
                        <td>
                            <div class="x_content">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-danger">点击操作</button>
                                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown"
                                            aria-expanded="false">
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">上架</a></li>
                                        <li><a href="#">下架</a></li>
                                        <li><a href="#">新增</a></li>
                                        <li><a href="#">修改</a></li>
                                        <li><a href="javascript:;">取消</a></li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div class="col-sm-5">
                <div class="dataTables_info" id="datatable-responsive_info"
                     role="status" aria-live="polite">共${pageBean.totalCount}条记录页
                </div>
            </div>
            <div class="col-sm-7">
                <div class="dataTables_paginate paging_simple_numbers"
                     id="datatable-responsive_paginate">
                    <ul class="pagination">
                        <c:if test="${pageBean.currentPage != 1}">
                            <li><a href="${prc}/app/getAppListPage?currentPage=1">首页</a></li>
                            <li class="paginate_button "><a
                                    href="${prc}/${pageBean.url}?currentPage=${pageBean.currentPage-1}&softwareName=${querySoftwareName}&status=${queryStatus}&flatformId=${queryStatus}&categoryLevel1=${queryCategoryLevel1}&categoryLevel2=${queryCategoryLevel2}&categoryLevel3=${queryCategoryLevel3}">上一页</a></li>
                        </c:if>
                        <c:if test="${pageBean.currentPage != pageBean.pageCount}">
                            <li class="paginate_button "><a
                                    href="${prc}/${pageBean.url}?currentPage=${pageBean.currentPage+1}&softwareName=${querySoftwareName}&status=${queryStatus}&flatformId=${queryStatus}&categoryLevel1=${queryCategoryLevel1}&categoryLevel2=${queryCategoryLevel2}&categoryLevel3=${queryCategoryLevel3}">下一页</a></li>
                            <li class="paginate_button "><a
                                    href="${prc}/app/getAppListPage?currentPage=${pageBean.pageCount}">尾页</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="common/footer.jsp" %>
</html>
