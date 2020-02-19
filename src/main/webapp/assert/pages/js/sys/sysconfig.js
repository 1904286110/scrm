//在页面渲染成功后，使用layui的use方法按需加载需要用的各个模块。
layui.use(['form' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	var form = layui.form;
	var layer = layui.layer;
	
	// 监听提交动作  submit(but_submit) = <button class="layui-btn" lay-submit lay-filter="but_submit">
	form.on('submit(but_config)', function(data) {
		
		
		$.ajax({
			type:'put',
			url:"sysconfig",
			data:$(data.form).serialize(),
			success:function(result){
				if(result){
					layer.msg("系统参数设置成功!");	
					
					
				}
			}
		});
		
		//不用忘记 return false ,讲按钮原来的功能给屏蔽掉。
		return false;
	});


});