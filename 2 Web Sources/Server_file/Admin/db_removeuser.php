<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 
 $verifyusernamesql = "select * from useraccount where username='$username'";
 $verifyusernamecheck = mysqli_fetch_array(mysqli_query($con,$verifyusernamesql));
 
 
 if(isset($verifyusernamecheck)){
 
           $Sql_delete_useraccount = "delete from useraccount where username = '$username'";
           $Sql_delete_licensedata = "delete from license_data where username = '$username'";
           $Sql_delete_licensetype = "delete from license_type where username = '$username'";
           $Sql_delete_displayveichel = "delete from display_veichle where username = '$username'";
           $Sql_delete_fine = "delete from userfine where username = '$username'";
           if(mysqli_query($con,$Sql_delete_fine) && mysqli_query($con,$Sql_delete_displayveichel) && mysqli_query($con,$Sql_delete_useraccount) &&  mysqli_query($con,$Sql_delete_licensedata) &&  mysqli_query($con,$Sql_delete_licensetype)){     
           echo 'You Revoked '.$username.'\'s License';
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