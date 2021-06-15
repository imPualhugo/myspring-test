<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/6/1
  Time: 3:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>类型管理</title>
</head>
<body>
<div>
    添加类型 :
    <button onclick="insertType()">新增</button>
</div>
<div>
    <div>
        <input id="search" style="width: 300px">
        <button onclick="search()">搜索</button>
    </div>
    共
    <div id="count" style="display: inline-block"></div>
    个
    <table id="types">

    </table>
    <div id="pages"></div>
</div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function () {
        getTypes();
    })

    function search() {
        getTypes(1)
    }

    function deleteType(id) {

        if (!confirm("确定删除?")){
            return;
        }

        $.ajax({
            url: "/day18_bqg_back/type/" + id,
            method: "delete",
            data: {"id": id},
            dataType: "json",
            success:function (data) {
                if (data.code == -1) {
                    alert(data.message);
                } else {
                    getTypes();
                }
            }});

    }

    function updateType(id) {
        var name = prompt("请输入新的名称");
        if (name == undefined || name == "") {
            alert("对不起,名称不能为空");
            return;
        }
        console.log(id+" : "+name);
        $.ajax({
            url: "/day18_bqg_back/type/update",
            method: "put",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({"id": id, "name": name}),
            // data: {"id": id, "name": name},
            dataType: "json",
            success:function (data) {
                if (data.code == -1) {
                    alert(data.message);
                } else {
                    getTypes();
                }
        }});
    }

    function insertType() {
        var name = prompt("请输入类型的名称");
        if (name == undefined || name == "") {
            alert("对不起,类型不能为空")
            return;
        }
        $.post("/day18_bqg_back/type/", {"name": name}, function (data) {
            if (data.code == 0) {
                alert("对不起,请先登录");
                location.href = "login.jsp";
                return;
            }
            if (data.code == -1) {
                alert(data.message);
            } else {
                getTypes();
            }

        }, "json");
    }

    //查询类型
    var pageN;

    var typeCount;

    function getTypes(pageNo) {
        //自己获取条件
        if (pageNo == undefined) {
            pageNo = 1;
        }
        var search = $("#search").val();

        $.ajax({
            url: "/day18_bqg_back/type/page/" + pageNo,
            method: "get",
            data: {"name": search},
            dataType: "json",
            success: function (data) { //code com.banyuan.message data
                if (data.code == 0) {
                    alert("对不起,请先登录");
                    location.href = "login.jsp";
                    return;
                }
                if (data.code == -1) {
                    alert(data.message);
                } else {
                    var list = data.data.list;
                    var html = "<tr><td>分类名称</td><td>创建时间</td><td>修改时间</td><td>操作</td></tr>";
                    list.forEach(function (item) {
                        html += "<tr>" +
                            "<td>" + item.name + "</td>" +
                            "<td>" + item.stringCreateTime + "</td>" +
                            "<td>" + item.stringUpdateTime + "</td>" +
                            "<td><button onclick='updateType(" + item.id + ")'>修改</button><button onclick='deleteType(" + item.id + ")'>删除</button></td>" +
                            "</tr>";
                    });
                    $("#types").html(html);

                    //拼接分页
                    var pageData = data.data;
                    typeCount = pageData.count;
                    $("#count").text(typeCount);
                    var pages = Math.ceil(pageData.count / pageData.pageSize)
                    var pageHtml = "";
                    for (let i = 1; i <= pages; i++) {
                        if (i == pageData.pageNo) {
                            pageHtml += "<button style='background-color: blue;color: white'>" + i + "</button>"
                        } else {
                            pageHtml += "<button  onclick='getTypes(" + i + ")'>" + i + "</button>"
                        }
                    }
                    $("#pages").html(pageHtml);
                    pageN = pageData.pageNo;
                }
            }
        });
    }
</script>
</html>
