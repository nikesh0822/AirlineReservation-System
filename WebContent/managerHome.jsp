<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>







<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Manager Home</title>

<link rel="stylesheet" href="css/style3.css">

<link rel="stylesheet" href="css/style4.css">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/smoothness/jquery-ui.css" />



</head>

<body>

  

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>

<script src="jQuery_files/jquery-1.11.1.min.js"></script>

<script src="jQuery_files/jquery-ui.min.js"></script>

<script  src="js/index.js"></script>

<style>

.ui-autocomplete {

max-height: 100px;

overflow-y: auto;

/* prevent horizontal scrollbar */

overflow-x: hidden;

}

* html .ui-autocomplete {

height: 100px;

}

</style>
<script type="text/javascript">
        $(function () {
   
            $("#city_id_jq").autocomplete({
            	
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "airportName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
            
            $("#CustName").autocomplete({
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "custName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
            
            $("#CName").autocomplete({
            	
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "custName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
            
            $("#DestCity").autocomplete({
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "city" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });

            
            $("#fl_nm_rs").autocomplete({
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "flight_number" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
           
        });
    </script>



<script>



function ed_opt_fn()

{

  //document.getElementById('div1').style.display ='none';

  $("#ed_opt1").hide();

  $("#ed_opt2").hide();

  $("#ed_opt3").hide();

  $("#ed_opt4").hide();

  $("#ed_opt5").hide();

  $("#ed_opt6").hide();

  $("#ed_opt7").hide();

}

function refresh_sel1(){

  if(document.getElementById('edit_details_sel').value == 'LN')

    {

    ed_opt_fn();

    $("#ed_opt1").show();

    }

  else if(document.getElementById('edit_details_sel').value == 'FN')

    {

    ed_opt_fn();

    $("#ed_opt2").show();

    }

  else if(document.getElementById('edit_details_sel').value == 'Add')

  {

    ed_opt_fn();

  $("#ed_opt3").show();

  }

  else if(document.getElementById('edit_details_sel').value == 'City')

  {

    ed_opt_fn();

  $("#ed_opt4").show();

  }

  else if(document.getElementById('edit_details_sel').value == 'SL')

  {

    ed_opt_fn();

  $("#ed_opt5").show();

  }

  else if(document.getElementById('edit_details_sel').value == 'ZC')

  {

    ed_opt_fn();

  $("#ed_opt6").show();

  }

  else if(document.getElementById('edit_details_sel').value == 'Tel')

  {

    ed_opt_fn();

  $("#ed_opt7").show();

  }

}

 function Check1() {

    if (document.getElementById('chk1').checked){

        others();

        document.getElementById('C1_Opt').style.display = 'block';

        document.getElementById('C2_Opt').style.display ='none';

    }

}

 function add_fun()

 {

   others();

   document.getElementById('add_details').style.display = 'block';

 }

 

 function edit_fun()

 {

   others();

   document.getElementById('edit_details').style.display = 'block';

 }

 

 function del_fun()

 {

   others();

   document.getElementById('del_details').style.display = 'block';

 }

 

 function others() {

          document.getElementById('reservations_sel').style.display ='none';

          document.getElementById('summary_sel').style.display ='none';

          document.getElementById('sales_sel').style.display ='none';

          document.getElementById('add_details').style.display ='none';

          document.getElementById('edit_details').style.display ='none';

          document.getElementById('del_details').style.display ='none';

          document.getElementById('FCC_Opt1').style.display ='none';

          document.getElementById('FCC_Opt2').style.display ='none';

          document.getElementById('SL_Opt1').style.display ='none';

          document.getElementById('SL_Opt2').style.display ='none';

          document.getElementById('SL_Opt3').style.display ='none';

          document.getElementById('C1_Opt').style.display ='none';

          document.getElementById('C2_Opt').style.display ='none';

  }

 

 function Disp_sel()

 {

   if (document.getElementById('Display_Month_Id').checked){

     others();

          document.getElementById('sales_sel').style.display = 'block';

      }

   

 }

 function Disp_summary()

 {

   if (document.getElementById('DisplaySummary').checked){

     others();

          document.getElementById('summary_sel').style.display = 'block';

      }

 }

 function Disp_Reservations()

 {

   if (document.getElementById('DisplayReservations').checked){

     others();

          document.getElementById('reservations_sel').style.display = 'block';

      }

 }

 function Check2() {

      if (document.getElementById('chk2').checked){

        others();

          document.getElementById('C2_Opt').style.display = 'block';

          document.getElementById('C1_Opt').style.display ='none';

      }

  }

 

 function FlightCustCheck() {

      if (document.getElementById('FlightCheck').checked){

        others();

          document.getElementById('reservations_sel').style.display = 'block';

          document.getElementById('FCC_Opt1').style.display = 'block';

          document.getElementById('FCC_Opt2').style.display ='none';

      }

      else if(document.getElementById('CustCheck').checked){

        others();

            document.getElementById('reservations_sel').style.display = 'block';

          document.getElementById('FCC_Opt2').style.display = 'block';

          document.getElementById('FCC_Opt1').style.display ='none';

      }

  }

   function SumList() {

        if (document.getElementById('FlightNumber').checked){

          others();

          document.getElementById('summary_sel').style.display = 'block';

            document.getElementById('SL_Opt1').style.display = 'block';

            document.getElementById('SL_Opt2').style.display ='none';

            document.getElementById('SL_Opt3').style.display ='none';

        }

        else if(document.getElementById('DestinationCity').checked){

          others();

          document.getElementById('summary_sel').style.display = 'block';

            document.getElementById('SL_Opt2').style.display = 'block';

            document.getElementById('SL_Opt1').style.display ='none';

            document.getElementById('SL_Opt3').style.display ='none';

        }

        else if(document.getElementById('CustomerName').checked){

          others();

          document.getElementById('summary_sel').style.display = 'block';

        document.getElementById('SL_Opt3').style.display = 'block';

        document.getElementById('SL_Opt1').style.display ='none';

        document.getElementById('SL_Opt2').style.display ='none';

      }

    }

 </script>







 <div class="login-page2">

  <div class="form2">

  <h1>Manager Home Page</h1>

  </div>

  </div>

   <div class="login-page">

  <div class="form">

 <form name = "ManagerPage" action = "ManagerServlet" method = "post">



<input type = "radio" id="radio-1" class="radio-custom" name = "manager_selected_option" onclick="javascript:add_fun()" value="add_customer_info"/><label for="radio-1" class="radio-custom-label">Add Info Customer</label><br>

<div id="add_details" style="display:none">

 <p style="padding-left: 3em"> 

Enter Last Name : <input type='text' id='#' name='firstname'> <br>

Enter First Name : <input type='text' id='#' name='lastname'> <br>

Enter Address : <input type='text' id='#' name='address'> <br>

Enter City : <input type='text' id='#' name='telephone'> <br>

Enter StateLine : <input type='text' id='#' name='city'> <br>

Enter Zipcode : <input type='text' id='#' name='stateline'> <br>

Enter Telephone : <input type='text' id='#' name='zipcode'> <br>

Enter Email : <input type='text' id='#' name='email'> <br>

Enter Credit Card Number : <input type='text' id='#' name='creditCardNumber'> <br>

Enter Password : <input type='password' id='#' name='password'> <br>

</p>

</div>



<br><input type = "radio" name = "manager_selected_option"  id="radio-2" class="radio-custom"  onclick="javascript:edit_fun()" value="edit_customer_info"/><label for="radio-2" class="radio-custom-label">Edit Info Customer</label> <br>

<div id="edit_details" style="display:none">

  <p style="padding-left: 3em"> 

Enter Account Number : <input type='text' id='#' name='customer_account_number'> <br>

Select value to edit

<select name ="field_being_modified" id="edit_details_sel" onchange="javascript:refresh_sel1()">

  <option value="LN">Last Name</option>

  <option value="FN">First Name</option>

  <option value="Add">Address</option>

  <option value="City">City</option>

  <option value="SL">StateLine</option>

  <option value="ZC">ZipCode</option>

  <option value="Tel">Telephone</option>

</select>

</p>

  <p id="ed_opt1" style="display:none;padding-left: 3em">

       Last Name  : <input type="text" name="lname_name_edit">

    </p>



    <p id="ed_opt2" style="display:none;padding-left: 3em">

        First Name : <input type="text" name="fname_name_edit">

    </p>

    <p id="ed_opt3" style="display:none;padding-left: 3em">

        Address : <input type="text" name="add_name_edit">

    </p>

    <p id="ed_opt4" style="display:none;padding-left: 3em">

        City : <input type="text" name="city_name_edit">

    </p>

    <p id="ed_opt5" style="display:none;padding-left: 3em">

        StateLine : <input type="text" name="state_name_edit">

    </p>

    <p id="ed_opt6" style="display:none;padding-left: 3em">

        ZipCode : <input type="text" name="zip_name_edit">

    </p>

    <p id="ed_opt7" style="display:none;padding-left: 3em">

        Telephone : <input type="text" name="tel_name_edit">

    </p>

  

</div>



<br><input type = "radio" name = "manager_selected_option"  id="radio-3" class="radio-custom"  onclick="javascript:del_fun()" value="delete_customer_info"/><label for="radio-3" class="radio-custom-label">Delete Info Customer </label><br>

<div id="del_details" style="display:none"><br>

  <p style="padding-left: 3em"> 

Enter Account Number : <input type='text' id='#' name='acc_number_to_be_deleted'>

</p>

</div>

<br>

<input type = "radio" name = "manager_selected_option"  id="radio-4" class="radio-custom"  onclick="javascript:others()" value="list_all_flights"/><label for="radio-4" class="radio-custom-label">List of All flights</label><br><br>

<input type = "radio" name = "manager_selected_option"  id="radio-5" class="radio-custom"  onclick="javascript:others()" value="most_revenue_customer"/><label for="radio-5" class="radio-custom-label">Customer who generated most total revenue</label> <br><br>

<input type = "radio" name = "manager_selected_option"  id="radio-6" class="radio-custom"  onclick="javascript:others()" value="most_active_flights"/><label for="radio-6" class="radio-custom-label">List of most active flights</label> <br><br> 

<input type = "radio" name = "manager_selected_option"  id="radio-7" class="radio-custom"  onclick="javascript:others()" value="flight_Status"/><label for="radio-7" class="radio-custom-label">List of all flights whose arrival and departure times are on-time/delayed</label> <br><br>







<input type = "radio" name = "manager_selected_option" class="radio-custom"  id = "chk1" onclick="javascript:Check1();" value="customers_on_flight"/><label for="chk1" class="radio-custom-label">List of all customers who have seats reserved on a given flight </label> <br>

<div id="C1_Opt" style="display:none"> <br>

  <p style="padding-left: 3em"> 

Enter Flight Number : <input type='text' id='fl_nm_rs' name='get_customer_data_for_flight_number'>

</p>

</div>

<br>



<input type = "radio" name = "manager_selected_option" class="radio-custom"  id = "chk2" onclick="javascript:Check2();" value="all_flights_that_stop_at_airport"/><label for="chk2" class="radio-custom-label">List of all flights for a given airport</label><br>

<div id="C2_Opt" style="display:none"> <br>

  <p style="padding-left: 3em"> 

Enter Airport : <input type='text' id='city_id_jq' name='airport_name'>

<br>

</p>

</div>

<br>

<input type = "radio" name = "manager_selected_option"  class="radio-custom"  id = "DisplayReservations" onclick="javascript:Disp_Reservations();" value="res_by_flight_number_or_cust"/><label for="DisplayReservations" class="radio-custom-label">List of Reservations by Flight Number OR Customer Name</label> <br>

 <div id="reservations_sel" style="display:none"> 

  <p style="padding-left: 3em"> <input type="radio" onclick="javascript:FlightCustCheck();" class="radio-custom" name="reservations" id="FlightCheck" value="fNumber_res"><label for="FlightCheck" class="radio-custom-label"> Flight Number &nbsp;&nbsp;&nbsp;&nbsp;</label>

  <input type="radio" onclick="javascript:FlightCustCheck();" name="reservations" class="radio-custom"  id="CustCheck" value="cName_res"><label for="CustCheck" class="radio-custom-label">Customer Name</label>

</p>



  <div id="FCC_Opt1" style="display:none">

    <p style="padding-left: 3em"> 

     Enter Flight Number : <input type='text' id='FlightNum' name='FlightNum'> 

    </p>

    </div>

    <div id="FCC_Opt2" style="display:none">

      <p style="padding-left: 3em"> 

     Enter Customer Name : <input type='text' id='CustName' name='CustName'>

    </p>

    </div> 

  

   </div>

    

    <br>

<input type = "radio" name = "manager_selected_option" class="radio-custom"  id = "DisplaySummary" onclick="javascript:Disp_summary();" value="revenue_listing"/><label for="DisplaySummary" class="radio-custom-label">Summary Listing of Revenue</label><br>

 <div id="summary_sel" style="display:none"> 

  <p style="padding-left: 3em"> 

  <input type="radio" onclick="javascript:SumList();" name="summary" class="radio-custom"  id="FlightNumber" value="fNumber_revSummary_radio_value"><label for="FlightNumber" class="radio-custom-label">Flight Number &nbsp;&nbsp;&nbsp;&nbsp;</label>

  <input type="radio" onclick="javascript:SumList();" name="summary" class="radio-custom"  id="DestinationCity" value="destCity_revSummary_radio_value"><label for="DestinationCity" class="radio-custom-label">Destination City &nbsp;&nbsp;&nbsp;&nbsp;</label>

  <input type="radio" onclick="javascript:SumList();" name="summary" class="radio-custom"  id="CustomerName" value="cName_revSummary_radio_value"><label for="CustomerName" class="radio-custom-label">Customer Name &nbsp;&nbsp;&nbsp;&nbsp;</label>

</p>

    <div id="SL_Opt1" style="display:none"> 

      <p style="padding-left: 3em"> 

        Enter Flight Number : <input type='text' id='FlightNum' name='FlightNumber'>

      </p>

    </div>

    <div id="SL_Opt2" style="display:none"> 

      <p style="padding-left: 3em"> 

       Enter Destination City : <input type='text' id='DestCity' name='DestCity'>

     </p>

    </div>

    <div id="SL_Opt3" style="display:none"> 

      <p style="padding-left: 3em"> 

        Enter Customer Name : <input type='text' id='CName' name='CustomerName'>

      </p>

    </div> 

</div>



<br>

<input type = "radio" name = "manager_selected_option" class="radio-custom" id = "Display_Month_Id" onclick="javascript:Disp_sel();" value="sales_report_for_month"/><label for="Display_Month_Id" class="radio-custom-label">Sales Report for Month</label><br>

<div id="sales_sel" style="display:none"> 

  <p style="padding-left: 3em"> 

Select Month :  

<select name="selected_month">

  <option value="1">January</option>

  <option value="2">February</option>

  <option value="3">March</option>

  <option value="4">April</option>

  <option value="5">May</option>

  <option value="6">June</option>

  <option value="7">July</option>

  <option value="8">August</option>

  <option value="9">September</option>

  <option value="10">October</option>

  <option value="11">November</option>

  <option value="12">December</option>

</select>

</p>



</div>

<br>

    <input type = "submit" value = "Submit" style="background-color: #4CAF50">



</form>

</div>

</div>

</body>

</html>

