<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" href="/statics/localcss/appinfolist.css">
    <%@include file="common/header.jsp" %>
</head>
<body>
<div class="row">
    <div class="x_panel">
        <div class="x_title">
            <h2>APP 信息维护 <i class="fa fa-user"></i>
                <small>${userSession.devName} - 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^</small>
            </h2>
            <ul class="nav navbar-right panel_toolbox"></ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_panel">
            <div class="x_content">
                <br/>
                <form class="form-horizontal form-label-left">
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control">
                                        <option>Choose option</option>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control">
                                        <option>Choose option</option>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control">
                                        <option>Choose option</option>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control">
                                        <option>Choose option</option>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select class="form-control">
                                        <option>Choose option</option>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <br/>
                        <li>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                </div>
                            </div>
                        </li>
                    </ul>

                </form>
            </div>
        </div>
        <div class="x_content">
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    <th>Age</th>
                    <th>Start date</th>
                    <th>Salary</th>
                </tr>
                </thead>


                <tbody>
                <tr>
                    <td>Tiger Nixon</td>
                    <td>System Architect</td>
                    <td>Edinburgh</td>
                    <td>61</td>
                    <td>2011/04/25</td>
                    <td>$320,800</td>
                </tr>
                <tr>
                    <td>Garrett Winters</td>
                    <td>Accountant</td>
                    <td>Tokyo</td>
                    <td>63</td>
                    <td>2011/07/25</td>
                    <td>$170,750</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div class="col-sm-5">
                <div class="dataTables_info" id="datatable-responsive_info"
                     role="status" aria-live="polite">共条记录页
                </div>
            </div>
            <div class="col-sm-7">
                <div class="dataTables_paginate paging_simple_numbers"
                     id="datatable-responsive_paginate">
                    <ul class="pagination">

                        <li class="paginate_button previous"><a
                                href="javascript:page_nav(document.forms[0],1);"
                                aria-controls="datatable-responsive" data-dt-idx="0"
                                tabindex="0">首页</a>
                        </li>
                        <li class="paginate_button "><a
                                href="javascript:page_nav(document.forms[0],${pages.currentPageNo-1});"
                                aria-controls="datatable-responsive" data-dt-idx="1"
                                tabindex="0">上一页</a>
                        </li>

                        <li class="paginate_button "><a
                                href="javascript:page_nav(document.forms[0],${pages.currentPageNo+1 });"
                                aria-controls="datatable-responsive" data-dt-idx="1"
                                tabindex="0">下一页</a>
                        </li>
                        <li class="paginate_button next"><a
                                href="javascript:page_nav(document.forms[0],${pages.totalPageCount });"
                                aria-controls="datatable-responsive" data-dt-idx="7"
                                tabindex="0">最后一页</a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="common/footer.jsp" %>
</html>
