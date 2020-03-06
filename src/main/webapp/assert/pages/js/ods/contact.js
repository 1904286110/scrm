
layui.use([  'laydate','table', 'form','upload' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	 var laydate = layui.laydate;
	 var upload = layui.upload;

	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'contact', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'contCode', title: '跟单编号', width:180}
		      ,{field: 'userName', title: '员工名称', width:180}
		      ,{field: 'contTypeVal', title: '跟单方式', width:180}
		      ,{field: 'contStatusVal', title: '跟单状态', width:180}
		      ,{field: 'custName', title: '客户名称'} //此次不设置width 让其自动适应
		      ,{title: '操作', width: 200,templet:'#contactBtnTpl'}
		    ]]
	});

	
	
	
});

