<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $registration_number = $_POST['registration_number'];
 $veichle_id = $_POST['veichle_id'];
 $veichle_type = $_POST['veichle_type'];
 $veichle_brand = $_POST['veichle_brand'];

 
 //Get username from table
 $verifyusernamesql = "select * from useraccount where username = '$username'";
 $vunc = mysqli_fetch_array(mysqli_query($con,$verifyusernamesql));
 
 //Get veichleid from addveichle table
 $verify_vid = "select * from display_veichle where veichle_id = '$veichle_id'";
 $vvc = mysqli_fetch_array(mysqli_query($con,$verify_vid));


 
 
 if(isset($vunc)){
 //echo "username exists";
 
 if(isset($vvc)){
 echo "WARNING: Veichle ID Exists You Can't Override";
 }
 else{
 
 $Sql_Query = "insert into display_veichle(username,registration_number,veichle_id,veichle_type,veichle_brand) values('$username','$registration_number','$veichle_id','$veichle_type','$veichle_brand')";
           if(mysqli_query($con,$Sql_Query)){
           echo "Data Uploaded Sucessfully";
 
           }
           else{
           echo 'Something went wrong';
           }
 
 }
 
 
 
 }
 else{
 echo "username dosent exists";
 }
 
 

 
  
  }

   ?>