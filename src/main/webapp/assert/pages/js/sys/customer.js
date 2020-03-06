var form;
var rate;
var upload;
layui.use([ 'table', 'form','upload','rate' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	 form = layui.form;
	 upload = layui.upload;
	 rate = layui.rate;
	var table = layui.table;
	//自定义表单校验
	form.verify({
		checkCustomerName:function(value, item){ //value：表单的值、item：表单的DOM对象
			//调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'customer/checkname');
		}
	});
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'customer', // 数据接口 layui会自动封装成  role/{page}/{limit}
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
	//绑定 省 change
	form.on('select(area1)', function(data){
		var parentCode =data.value;
		console.log(data.value);
		initAreaData(parentCode,'#cityCode');
		
		});
    
	//绑定 市 change
	form.on('select(area2)', function(data){
		var parentCode =data.value;
		console.log(data.value);
		initAreaData(parentCode,'#areaCode');
		
		});

});

//查询二级的Area数据
function initAreaData(parentCode,selectId,checkedCode){

return $.ajax({
		type:'get',
		url:'customer/list/'+parentCode,
		success:function(areaList){
			var options ='<option value=>请选择</option>';

			if(areaList){
				$.each(areaList,function(index,area){
					var code = area.areaCode;
					var areaName = area.areaName;
					var selected='';
					if(checkedCode&&checkedCode==code){
						selected='selected';
					}
					var option ='<option value="'+code+'" '+selected+'>'+areaName+'</option>';
					options +=option;
				});
				
				
				 
			}
			$(selectId).html(options);
			console.log(options);
			form.render('select');
		}
	});
}
 function handerEdit(){
	$.ajax({
		type: 'get',
		url: 'customer/'+$('#hideRowId').val(),
		success : function(user){
			var proCode = user.proCode ;
			var cityCode = user.cityCode ;
			var areaCode = user.areaCode ;
			var cusKind=user.cusKind;
			initAreaData(proCode,'#cityCode',cityCode).done(function(){
				initAreaData(cityCode,'#areaCode',areaCode) ;
			});
			 rate.render({
				    elem: '#cusKind_rate'
				    ,value: cusKind //初始值
				    ,choose: function(value){
				       $("#cusKind").val(value);
				      }
				    ,text: true //开启文本
				  });
			 
			upload.render({
				elem : '#custUpload',
				url : 'upload', //上传接口
				accept : 'file', //普通文件
				field: 'uploadFile', //设定文件域的字段名默认值是file
				data: {"uploadType":2},//
				done : function(res) {
					$('#cusEnclosure').val(res.data);
					
				}
			});

		}
	});
}
function initForm(){
	upload.render({
		elem : '#custUpload',
		url : 'upload', //上传接口
		accept : 'file', //普通文件
		field: 'uploadFile', //设定文件域的字段名默认值是file
		data: {"uploadType":2},//
		done : function(res) {
			$('#cusEnclosure').val(res.data);
			
		}
	});
  
	//显示文字
	  rate.render({
	    elem: '#cusKind_rate'
	    ,value: 2 //初始值
	    ,choose: function(value){
	       $("#cusKind").val(value);
	      }
	    ,text: true //开启文本
	  });



}