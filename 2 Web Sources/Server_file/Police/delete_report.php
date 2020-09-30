<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 //$reportid = "akash##28/07/1998##7:30";//$_POST['reportid'];
 $reportid = $_POST['reportid'];
 
 $rs = explode("##",$reportid);
 
 $username= $rs[0];
 $date= $rs[1];
 $time= $rs[2];
 
 //echo $rs[0];
 
 
 
 $verifyusernamesql = "select * from userreports where username = '$username' and dates = '$date' and times = '$time'";
 $verifyusernamecheck = mysqli_fetch_array(mysqli_query($con,$verifyusernamesql));
 
 
 if(isset($verifyusernamecheck)){
 
           $Sql_delete_userreport = "delete from userreports where username = '$username' and dates = '$date' and times = '$time'";
          
           if(mysqli_query($con,$Sql_delete_userreport)){     
           echo $username.' Report Deleted';
           }
           else{
           echo 'Something went wrong';
           }
     }

else{

echo "No Reports Found #ERROR";

}
   
   
     
   
}
  
  

   ?>