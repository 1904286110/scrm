    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#5cbdaa',
                lineColor:'#5cbdaa'
            });
        });

        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
         /*   if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if (data.captcha == '') {
                layer.msg('验证码不能为空');
                return false;
            }*/
            $.ajax({
       		 type:'get',
       		 url:'dologin',
       		 data:$('#form_login').serialize(),
       		 success:function(result){
       			var code=result.code;
       			if(code==0){
       				layer.msg(result.msg);
       			}else if(code==1){
       				layer.msg('登录成功');
       			 window.location = '/scrm/index';
       			}
       		 }
       	 });
           
            return false;
        });
    });