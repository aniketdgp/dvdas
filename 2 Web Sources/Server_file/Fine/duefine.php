<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 
 $CheckSQLuser = "SELECT * FROM useraccount WHERE username ='$username'";
 $checkuser = mysqli_fetch_array(mysqli_query($con,$CheckSQLuser));
 

 $GetFineAmount = "Select fineamount from userfine where username = '$username'";
 $FineArray = mysqli_query($con,$GetFineAmount);
 $TotalDue = 0;
 
 while($row = mysqli_fetch_array($FineArray)){
 
  $TotalDue = $TotalDue+$row['fineamount'];
  
}

   
 if(isset($checkuser) && $TotalDue+$fineamount<=0){
 echo $TotalDue;
 }
 else{
 echo "Something Went Wrong";
 }
 
 
   
}
   
 ?>