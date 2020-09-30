<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $user_password = $_POST['user_password'];

 $CheckSQL = "SELECT * FROM userpolice WHERE username ='$username'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Police Username Already Exist';

 }
else{ 
$Sql_Query = "INSERT INTO userpolice(username,user_password) values ('$username','$user_password')";

 if(mysqli_query($con,$Sql_Query))
 {
    echo 'Added New Police to System';
   }
   else
   {
    echo 'Something went wrong';
    }
    }
   }
   // mysqli_close($con);
   ?>