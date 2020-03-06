<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="auth" uri="/auth-tags" %>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">系统设置</div>
				<div class="layui-card-body">
					<!-- 表单 开始  -->
					<form class="layui-form" action="">
					
	                   <input type="hidden" value="${sysConfig.rowId}" name="rowId"/>
						<div class="layui-form-item">
							<label class="layui-form-label">公司名称</label>
							<div class="layui-input-block">
								<input type="text" name="comName" required lay-verify="required" value="${sysConfig.comName}"
									placeholder="请输入公司名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">公海天数</label>
							<div class="layui-input-block">
								<input type="text" name="cusDay" required lay-verify="required" value="${sysConfig.cusDay}"
									placeholder="请输入公海天数" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">提醒日期</label>
							<div class="layui-input-block">
								<input type="text" name="orderDay" required lay-verify="required" value="${sysConfig.orderDay}"
									placeholder="请输入提醒日期" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<div class="layui-input-block">
							<auth:have url="sysconfig" method="put">
								<button class="layui-btn" lay-submit lay-filter="but_config">立即提交</button>
							</auth:have>	
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</div>





<!-- 引入自定义的JS脚本 -->
<script src="assert/pages/js/sys/sysconfig.js"></script>
