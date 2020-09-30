<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $reason = $_POST['reason'];
 $date = $_POST['date'];
 $time = $_POST['time'];
 $location = $_POST['location'];
 

 $CheckSQL = "SELECT * FROM useraccount WHERE username ='$username'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

   $SqlQuery = "Insert into userreports(username,reason,dates,times,location)value('$username','$reason','$date','$time','$location')";
  
  
  if(mysqli_query($con,$SqlQuery)){
    
    echo 'Your Report Is Submited';
  
  }else{
   
   echo 'Something went wrong';
   
   }
       

 }else{
 
 echo 'User Doesnt Exists';
 
 }
 

}



?>