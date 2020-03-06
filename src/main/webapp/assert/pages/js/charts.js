 var $;
  layui.use('layer', function() {
	  $ = layui.$;
	  initBarData();
	  initPieData();
  });
  function initBarData() {
	  var config = {
			    type: 'bar',
			    data: {
			        
			        datasets: [{
			            label: '跟单方式',
			            data: [12, 19, 3, 5, 2, 3],
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255, 99, 132, 1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            ],
			            borderWidth: 1
			        }]
			    }, 
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero: true
			                }
			            }]
			        }
			    }
	  }
			    var contType_chart = new Chart('chart-1', config);
				$.ajax({
					
					url:'charts/conttype',
					
					success:function(cartDataList){
						if(cartDataList){
							var _data = contType_chart.data;
							var _datasets =_data.datasets[0];
							$.each(cartDataList,function(index,chartData){
								_datasets.data[index] =chartData.dataCount;
								_data.labels[index] =chartData.keyVal;
							});
							
						}
						contType_chart.update();
					}
				}); 
			    
			    
			    
			    
	  }
  
  
 function initPieData(){
	 var config = {
			 type :'doughnut', //甜圈圈 饼图 
			 data : {
				 datasets :[{
					 data:[],
					 backgroundColor :[ window.chartColors.red,
						 window.chartColors.orange, window.chartColors.green,
						 window.chartColors.blue,window.chartColors.yellow,]
				 }],
				 labels: []
			 },
			 options : {
				 responsive : true
			 }
	 };
	 var custFrom_chart = new Chart('chart-0',config);
		$.ajax({
			
			url:'charts/contstatus',
			
			success:function(cartDataList){
				if(cartDataList){
					var _data = custFrom_chart.data;
					var _datasets =_data.datasets[0];
					$.each(cartDataList,function(index,chartData){
						_datasets.data[index] =chartData.dataCount;
						_data.labels[index] =chartData.keyVal;
					});
					
				}
				custFrom_chart.update();
			}
		}); 
	   
 }
