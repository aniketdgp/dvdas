<?php

 include '../DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $username = $_POST['username'];
 
 
 $Sql_Query = "select * from display_veichle";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 
 /*
 
 */ 
 
 
 
 if(isset($check)){
 
 $r = "select * from display_veichle";
 $result = mysqli_query($con,$r);
 
 $rows = array();
   while($rp = mysqli_fetch_assoc($result)) {
     $rows['v_info'][] = $rp;
   }

 print json_encode($rows);

 }
 
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 
 
 
 
?>