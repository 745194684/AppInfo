$(function () {
    let pathName = window.document.location.pathname;
    let projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1); //项目名
    // 点击一级分类显示二级分类
    $("#queryCategoryLevel1").change(function () {
        let pid = $(this).val();
        if (pid == -1) {
            $("#queryCategoryLevel2").html("<option value=\"-1\">--请选择--</option>");
            $("#queryCategoryLevel3").html("<option value=\"-1\">--请选择--</option>");
        } else {
            $.get(projectName + "/app/getCategoryByParentId?pid=" + pid, function (data) {
                // 清空原来的内容
                $("#queryCategoryLevel2").html("");
                // 添加新的内容
                let content = "<option value=\"-1\">--请选择--</option>";
                for (let i = 0; i < data.length; i++) {
                    content += "<option value=" + data[i].id + ">" + data[i].categoryName + "</option>";
                }
                // 显示
                $("#queryCategoryLevel2").html(content);
                $("#queryCategoryLevel3").html("<option value=\"-1\">--请选择--</option>");
            }, "json");
        }
    });
    // 点击二级分类显示三级分类
    $("#queryCategoryLevel2").change(function () {
        let pid = $(this).val();
        if (pid == -1) {
            $("#queryCategoryLevel3").html("<option value=\"-1\">--请选择--</option>");
        } else {
            $.get(projectName + "/app/getCategoryByParentId?pid=" + pid, function (data) {
                console.log(data);
                // 清空原来的内容
                $("#queryCategoryLevel3").html("");
                // 添加新的内容
                let content = "<option value=\"-1\">--请选择--</option>";
                for (let i = 0; i < data.length; i++) {
                    content += "<option value=" + data[i].id + ">" + data[i].categoryName + "</option>";
                }
                // 显示
                $("#queryCategoryLevel3").html(content);
            }, "json");
        }
    })


    /*$("#search_btn").click(function () {
        let url = new Array();
        let softwareName = $("#softwareName").val();
        if (url != null && url !== "") {
            url.push("?softwareName=");
            url.push(softwareName);
        }
        let status = $("#status").val();
        if (status != -1) {
            url.push("&status=");
            url.push(status);
        }
        let flatformId = $("#flatformId").val();
        if (flatformId != -1) {
            url.push("&flatformId=");
            url.push(flatformId);
        }
        let categoryLevel1 = $("#queryCategoryLevel1").val();
        if (categoryLevel1 != -1) {
            url.push("&categoryLevel1=");
            url.push(categoryLevel1);
        }
        let categoryLevel2 = $("#queryCategoryLevel2").val();
        if (categoryLevel2 != -1) {
            url.push("&categoryLevel2=");
            url.push(categoryLevel2);
        }
        let categoryLevel3 = $("#queryCategoryLevel3").val();
        if (categoryLevel3 != -1) {
            url.push("&categoryLevel3=");
            url.push(categoryLevel3);
        }
        let urlStr = url.join("");
        alert(typeof urlStr);
        $.ajax({
            url:projectName + "/app/getAppListPage" + urlStr,
            method:"get",
            async: true,
            success:function (data) {
                $("body").html(data);
            }
        })
    });*/

});

