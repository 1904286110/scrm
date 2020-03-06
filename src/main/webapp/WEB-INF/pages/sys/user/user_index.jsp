<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="auth" uri="/auth-tags"%>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					用户管理
					<!-- 新增按钮 开始 -->
					<auth:have url="user" method="post">
						<button type="button" class="layui-btn layui-btn-sm layui-btn-add">
							<i class="layui-icon layui-icon-addition"></i>新增
						</button>
					</auth:have>
					<!-- 新增按钮 结束 -->
				</div>
				<div class="layui-card-body">
					<!-- 搜索表单 开始  -->
					<form class="layui-form" id="form_search">
						<div class="layui-search-form">

							<div class="layui-inline">
								<input name="userName" placeholder="用户名称" autocomplete="off"
									class="layui-input">
							</div>
							<div class="layui-inline">
								<input name="userCode" placeholder="登录账号" autocomplete="off"
									class="layui-input">
							</div>
							<div class="layui-inline">
								<!-- 搜索按钮 -开始 -->
								<button class="layui-btn layui-btn-primary layui-btn-sm"
									lay-submit lay-filter="btn_search">
									<i class="layui-icon layui-icon-search"></i>
								</button>
								<!-- 搜索按钮 -结束 -->
								<!-- 重置按钮 - 开始 -->
								<button type="reset"
									class="layui-btn layui-btn-primary layui-btn-sm">
									<i class="layui-icon layui-icon-refresh"></i>
								</button>
								<!-- 重置按钮 - 结束 -->
							</div>
						</div>
					</form>
					<!-- 搜索表单 结束  -->
					<!-- 页面表格 开始  -->
					<table id="list_table" lay-filter="filter_table"></table>
					<!-- 页面表格 结束  -->
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 页面上的隐藏域，提供信息给通用的JS脚本使用 -->
<input type="hidden" id="hideURL" value="user" />
<input type="hidden" id="hideTitle" value="用户" />
<!-- 修改，删除 按钮 -->
<!-- 此处注意：必须有lay-event 才能通过table.on完成事件的绑定 -->
<script type="text/html" id="userLockTpl">
{{# if(d.userKind ==1){ }}
<input type="checkbox" value = {{d.rowId}}  lay-skin="switch" disabled  lay-text="解锁|锁定" {{d.isLock ==1 ? 'checked' : '' }}>
 {{#  } else { }}
<input type="checkbox" value = {{d.rowId}}  lay-skin="switch" lay-filter="switchTest" lay-text="解锁|锁定" {{d.isLock ==1 ? 'checked' : '' }}>
{{#  } }}
	

</script>
<script type="text/html" id="userKindTpl">
 {{# if(d.userKind ==1){ }}
    <span class="layui-badge layui-bg-danger">超级用户</span>
  {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">普通用户</span>
  {{#  } }}
</script>

<script type="text/html" id="userBtnTpl">
    {{# if(d.userKind ==1){ }}

    <auth:have url="user" method="put">
	<a class="layui-btn layui-btn-xs layui-btn-disabled">修改</a>
</auth:have>
<auth:have url="user/1" method="delete">
	<a class="layui-btn layui-btn-xs layui-btn-disabled">删除</a>
</auth:have>
   
{{# }else{  }}
    <auth:have url="user" method="put">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
</auth:have>
<auth:have url="user/1" method="delete">
	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
</auth:have>
{{#  } }}
</script>

<!-- 引入自定义的JS脚本 -->
<script src="assert/pages/js/sys/user.js"></script>
