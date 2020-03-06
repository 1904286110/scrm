<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chart.js - Html5 chart plugins</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body>
  
  
    
    
    <div class="container">
      <div class="row">
        <div class="col-sm-6">
          <h2 class="text-center">Line Chart</h2>
          <canvas id="lines-graph"></canvas>
        </div>
        <div class="col-sm-6">
          <h2 class="text-center">Bars Chart</h2>
          <canvas id="bars-graph"></canvas>
        </div>
        
    
        
      </div>
    </div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js"></script>
    <script>
    
    // Data for line charts
                    var lineChartData = {
                        labels: ["January", "February", "March", "April", "May", "June", "July"],
                        datasets: [
                        {
                            label: "My First dataset",
                            fill: false,
                            lineTension: 0.1,
                            backgroundColor: "rgba(75,192,192,0.4)",
                            borderColor: "rgba(75,192,192,1)",
                            borderCapStyle: 'butt',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "rgba(75,192,192,1)",
                            pointBackgroundColor: "#fff",
                            pointBorderWidth: 1,
                            pointHoverRadius: 5,
                            pointHoverBackgroundColor: "rgba(75,192,192,1)",
                            pointHoverBorderColor: "rgba(220,220,220,1)",
                            pointHoverBorderWidth: 2,
                            pointRadius: 1,
                            pointHitRadius: 10,
                            data: [65, 59, 80, 81, 56, 55, 40],
                            spanGaps: false,
                        }
                        ]
                    };

                    var ctx = document.getElementById("lines-graph").getContext("2d");
                    var LineChart = new Chart(ctx, {
                        type: 'line',
                        data: lineChartData,
                        responsive: true,
                        bezierCurve : false
                    });
    
    
    
      // Bar Charts
      var barChartData = {
          labels: ["January", "February", "March", "April", "May", "June", "July"],
          datasets: [
          {
              label: "My First dataset",
              backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1,
              data: [65, 59, 80, 81, 56, 55, 40],
          }
          ]
      };
  
      var ctx = document.getElementById("bars-graph").getContext("2d");
      var BarChart = new Chart(ctx, {
          type: 'bar',
          data: barChartData,
          responsive : true
      });
      
// Data for pie chart
                    var pieData = {
                        labels: [
                        "Red",
                        "Blue",
                        "Yellow"
                        ],
                        datasets: [
                        {
                            data: [300, 50, 100],
                            backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                            ],
                            hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                            ]
                        }]
                    };

                    var ctx = document.getElementById("pie-graph").getContext("2d");
                    var PieChart = new Chart(ctx,{
                        type: 'pie',
                        data: pieData
                    });
                    
                    
// Data for doughnut chart
                    var doughnutData = {
                        labels: [
                        "Red",
                        "Blue",
                        "Yellow"
                        ],
                        datasets: [
                        {
                            data: [300, 50, 100],
                            backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                            ],
                            hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                            ]
                        }]
                    };

                    var ctx = document.getElementById("doughnut-graph").getContext("2d");
                    var DoughnutChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: doughnutData,
                        responsive : true
                    });                    
                    
                    
                    
        </script>
  </body>
</html>