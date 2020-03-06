var form;

var upload;
layui.use([ 'laydate','table', 'form','upload' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	 form = layui.form;
	 upload = layui.upload;
	 var laydate = layui.laydate;
	var table = layui.table;

	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'public', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'cusName', title: '客户名称', width:180}
		      ,{field: 'cusPhone', title: '手机号码', width:180}
		      ,{field: 'cusContact', title: '联系人', width:180}
		      ,{field: 'cusInfo', title: '备注'} 
		      ,{title: '操作', width: 200,templet:'#customerBtnTpl'}
		    ]]
	});
	
	

	  


});

