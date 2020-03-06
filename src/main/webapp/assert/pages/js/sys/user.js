layui.use(['util','form','table'],function(){
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	var util = layui.util;
	//自定义校验
	form.verify({
	
		checkusercode:function(value,item){
			//调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'user/checkcode');
		}
	});
	
	 //监听开关事件
    form.on('switch(switchTest)', function (data) {
    	var islock=0;
    	var rowId=data.value;
        console.log(data.elem.checked); //开关是否开启，true或者false
        if(data.elem.checked){
        	islock=1;
        }
		$.ajax({
			type:'put',
			url:'user/dolock/',
			data:"rowId="+rowId+"&islock="+islock,
			success:function(result){
				if(result){
					
					table.reload('list_table');
					
					
				}
			}
		});
    });
	
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'user', 
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'userKind', title: '用户类型', width:100,templet:'#userKindTpl'}
		      ,{field: 'roleName', title: '角色类型', width:180}
		      ,{field: 'userName', title: '用户名称', width:150}
		      ,{field: 'userCode', title: '登录账号', width:180}
		      ,{field: 'createDate', title: '创建时间', templet: function (d) {
                  return util.toDateString(d.createDate);
              }, width: 180
          }
		      ,{title: '状态', width: 180,templet:'#userLockTpl'}
		      ,{title: '操作', templet:'#userBtnTpl'}
		    ]]
	});
	
});