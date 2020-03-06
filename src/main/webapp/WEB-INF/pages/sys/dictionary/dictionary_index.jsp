<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入自定义的权限管理标签 -->
<%@ taglib prefix="auth" uri="/auth-tags" %>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					资源管理
					<!-- 使用自定义标签在页面上判断 button或是超链接 是否可以显示 -->
					<!-- 新增按钮 开始 -->
					<auth:have url="dictionary" method="post">
					<button type="button" class="layui-btn layui-btn-sm layui-btn-add">
						<i class="layui-icon layui-icon-addition"></i>新增
					</button>
					</auth:have>
					<!-- 新增按钮 结束 -->
				</div>
				<div class="layui-card-body">
					<!-- 页面表格 开始  -->
					<table id="list_table" lay-filter="filter_table"></table>
					<!-- 页面表格 结束  -->
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="hideURL" value="dictionary"/>
<input type="hidden" id="hideTitle" value="字典"/>
<!-- 表格操作列 -->
<script type="text/html" id="dictionaryBtnTpl">
<auth:have url="dictionary/child/1" method="get">
	<a class="layui-btn  layui-btn-xs" lay-event="dic_child">新增子资源</a>
</auth:have>
<auth:have url="dictionary" method="put">
    <a class="layui-btn  layui-btn-xs" lay-event="dic_edit">修改</a>
</auth:have>
<auth:have url="dictionary/1" method="delete">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="dic_del">删除</a>
</auth:have>
</script>

<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/sys/dictionary.js"></script>
