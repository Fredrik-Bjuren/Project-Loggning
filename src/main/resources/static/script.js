
            //sum Table Time Total
            var table = document.getElementById("table")
            var sumTime = 0;
            var sumWork = 0;
            var sumPaidLeave = 0;
            var sumSickLeave = 0;
            var sumUnpaidLeave = 0;
            var sumOvertime = 0;

            for(var i = 1; i < table.rows.length; i++){
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

   
function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("table");
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc";
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++;
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

