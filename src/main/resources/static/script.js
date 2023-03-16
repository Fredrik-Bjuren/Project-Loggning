
            //sum Table Time Total
            var table = document.getElementById("table")
            var sumTime = 0;
            var sumWork = 0;
            var sumPaidLeave = 0;
            var sumSickLeave = 0;
            var sumUnpaidLeave = 0;
            var sumOvertime = 0;

            for(var i = 1; i < table.rows.length; i++)

            {
                var sumRow= parseInt(table.rows[i].cells[1].innerText);
                sumTime = sumTime + sumRow;
                switch(table.rows[i].cells[2].innerText){

                case "Work":
                sumWork = sumWork + sumRow;
                break;

                case "Paid Leave":
                sumPaidLeave = sumPaidLeave + sumRow;
                break;

                case "Sick Leave":
                sumSickLeave = sumSickLeave + sumRow;
                break;

                case "Overtime":
                sumOvertime = sumOvertime + sumRow;
                break;
                
                case "Unpaid Leave":
                sumUnpaidLeave = sumUnpaidLeave + sumRow;

                }
            }

            document.getElementById("sumTime").innerText = "Time Total: " + sumTime;



             // Load the Visualization API and the corechart package.
                                google.charts.load('current', {'packages':['corechart']});

                                // Set a callback to run when the Google Visualization API is loaded.
                                google.charts.setOnLoadCallback(drawChart);

                                // Callback that creates and populates a data table,
                                // instantiates the pie chart, passes in the data and
                                // draws it.
                                function drawChart() {

                                    // Create the data table.
                                    var data = new google.visualization.DataTable();
                                    data.addColumn('string', 'Category');
                                    data.addColumn('number', 'Hours');
                                    data.addRows([
                                        ['Work', sumWork],
                                        ['Overtime', sumOvertime],
                                        ['Paid Leave', sumPaidLeave],
                                        ['Unpaid Leave', sumUnpaidLeave],
                                        ['Sick Leave', sumSickLeave]

                                    ]);

                                    // Set chart options
                                    var options = {'title':'Monthly Hour Distribution',
                                        'width':400,
                                        'height':300};

                                    // Instantiate and draw our chart, passing in some options.
                                    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
                                    chart.draw(data, options);

                                }

   


