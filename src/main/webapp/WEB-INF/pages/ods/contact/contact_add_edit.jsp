<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_contact_add">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="userCode" value="${sessionScope.login_user.userCode}" id="hideRowId" />

	<div class="layui-form-item">
		<label class="layui-form-label">客户名称</label>
		<div class="layui-input-block">
			
			<input name="custName"
				id="custName" class="layui-input" placeholder="请输入客户名称"
				autocomplete="off">
				<input type="hidden" name="contCode" id="contCode" />
		</div>
	</div>


	<div class="layui-form-item layui-form-text">
		<c:if test="${!empty dictionaryList}">
			<c:forEach items="${dictionaryList}" var="dictionary">
				<div class="layui-inline">
					<c:if test="${(dictionary.parentKey eq 'S000')&&(dictionary.activeFlag==0)}">
						<c:set value="${dictionary.children}" var="childDictionaryList"></c:set>
						<label class="layui-form-label">${dictionary.dictInfo}</label>
						<div class="layui-input-inline">
							<select name="${dictionary.dictValue}" lay-verify="required" >
								<option value="">请选择</option>
								<c:if test="${!empty childDictionaryList}">
									<c:forEach items="${childDictionaryList}" var="childDictionary">
										<option value="${childDictionary.dictValue}">${childDictionary.dictInfo}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</c:if>
				</div>
			</c:forEach>
		</c:if>

	</div>
	<div class="layui-form-item layui-form-text">
<div class="layui-inline">
			<label class="layui-form-label">下次联系时间</label>
		<div class="layui-input-inline">
			 <input type="text" class="layui-input"  name="nextContDate" id="nextContDate">
			
		</div>
</div>
<div class="layui-inline">
			<label class="layui-form-label">附件</label>
		<div class="layui-input-inline">
			<button type="button"   class="layui-btn" id="contUpload">
				<i class="layui-icon">&#xe67c;</i>上传附件
			</button>
			<input type="hidden" lay-verify="required" name="contFile" id="contFile" />
		</div>
</div>

      <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">详细内容</label>
    <div class="layui-input-block">
      <textarea name="contInfo" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
  </div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
