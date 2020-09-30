<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include '../DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $username = $_POST['username'];
 $user_password = $_POST['user_password'];
 
 $Sql_Query = "select * from useraccount where username = '$username' and user_password = '$user_password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 }else{
 echo "Check Again";
 }
//mysqli_close($con);

?>