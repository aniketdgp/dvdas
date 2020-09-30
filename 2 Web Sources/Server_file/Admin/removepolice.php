<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 
 $verifyusernamesql = "select * from userpolice where username='$username'";
 $verifyusernamecheck = mysqli_fetch_array(mysqli_query($con,$verifyusernamesql));
 
 
 if(isset($verifyusernamecheck)){
 
           $Sql_delete_useraccount = "delete from userpolice where username = '$username'";
         
           if(mysqli_query($con,$Sql_delete_useraccount)){     
           echo 'You Removed '.$username.'\'s Acount';
           }
           else{
           echo 'Something went wrong';
           }
     }

else{

echo $username." dosent exists in our database";

}
   
   
   




   
   
  }

   ?>